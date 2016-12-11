package de.haw.aim.importer;


import de.haw.aim.authentication.persistence.UserRepository;
import de.haw.aim.importer.dto.ProductDTO;
import de.haw.aim.importer.dto.VendorDTO;
import de.haw.aim.uploadcenter.facade.IUploadCenter;
import de.haw.aim.vendor.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.naming.ServiceUnavailableException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataImporter
{
    //TODO extract to config file
    private static final String urlToCall = "http://132.252.58.189:8080/ontology/vendors";

    private VendorInfoRepository vendorInfoRepository;

    private VendorRepository vendorRepository;

    private ProductInfoRepository productInfoRepository;

    private UserRepository userRepository;

    private IUploadCenter iUploadCenter;

    @Autowired
    public DataImporter(VendorInfoRepository vendorInfoRepository, VendorRepository vendorRepository, ProductInfoRepository productInfoRepository, UserRepository userRepository, IUploadCenter iUploadCenter) {
        this();
        this.vendorInfoRepository = vendorInfoRepository;
        this.vendorRepository = vendorRepository;
        this.productInfoRepository = productInfoRepository;
        this.userRepository = userRepository;
        this.iUploadCenter = iUploadCenter;
    }

    private RestTemplate restTemplate;

    private DataImporter()
    {
        this.restTemplate = new RestTemplate();
    }

    private List<VendorDTO> getData() throws ServiceUnavailableException
    {
        // Try and call the symphony API, result holds VendorDTO
        ResponseEntity<List<VendorDTO>> exchange =
                restTemplate.exchange(urlToCall, HttpMethod.GET, null, new ParameterizedTypeReference<List<VendorDTO>>()
                {
                });
        // If HTTP Code is not 200 we bail out, because something is wrong
        if (exchange.getStatusCode() != HttpStatus.OK)
        {
            throw new ServiceUnavailableException();
        }

        return exchange.getBody();
    }

    public void synchronize() throws ServiceUnavailableException
    {
        // Get all the data from symphony api
        List<VendorDTO> apiReturn = getData();

        // Get all vendorinfos from our database
        List<VendorInfo> vendorInfos = vendorInfoRepository.findAll();

        // Find entries which mach to api ids, these need to be updated
        List<VendorDTO> vendorUpdateCandidates =
                        apiReturn.
                        stream().
                        filter(theirEntry -> vendorInfos.stream().
                                anyMatch(ourEntry -> theirEntry.getId().equals(ourEntry.getId()))).
                                collect(Collectors.toList());

        // every entry on apiReturn and not on vendorUpdateCandidates needs to be created
        List<VendorDTO> vendorCreateCandidates =
                        apiReturn.
                        stream().
                        filter(theirEntry -> vendorUpdateCandidates.stream().
                                anyMatch(ourEntry -> !theirEntry.getId().equals(ourEntry.getId()))).
                        collect(Collectors.toList());

        // every entry which are in our database and not in vendorUpdateCandidates need to be deleted
        List<VendorInfo> vendorsToDelete = vendorInfoRepository.findAll();
        vendorUpdateCandidates.
                forEach(theirEntry -> vendorsToDelete.
                        removeIf(ourEntry -> theirEntry.getId().equals(ourEntry.getId())));

        removeVendors(vendorsToDelete);
        updateVendors(vendorUpdateCandidates);
        createVendor(vendorCreateCandidates);

    }

    private void createVendor(List<VendorDTO> createCandidates)
    {
        for (VendorDTO vendorDTO : createCandidates)
        {
            // Create the vendorInfo entity
            VendorInfo vendorInfoToCreate = new VendorInfo(vendorDTO.getId(),vendorDTO.getLabel());
            vendorInfoToCreate = vendorInfoRepository.save(vendorInfoToCreate);

            // For a new vendor every product has to be new, so we only need to create products for this
            List<ProductInfo> productInfos = createProducts(vendorDTO.getProdukts());

            // Finally create the new vendor which brings productinfos and vendorinfo together
            Vendor vendor = new Vendor(vendorInfoToCreate,productInfos);
            vendorRepository.save(vendor);
        }
    }

    private void updateVendors(List<VendorDTO> updateCandidates)
    {
        // for every updateCandidate 
        for (VendorDTO vendorDTO : updateCandidates)
        {
            // find the vendorInfo entity in database based on the ID
            VendorInfo vendorInfoToUpdate = vendorInfoRepository.findOne(vendorDTO.getId());
            
            // set the name to the new name
            vendorInfoToUpdate.setName(vendorDTO.getLabel());
            
            // synchronize every product for this vendor
            synchronizeVendorProducts(vendorDTO, vendorInfoToUpdate);
            
            // save the updated vendorInfo entity to database
            vendorInfoRepository.save(vendorInfoToUpdate);
        }
    }

    private void removeVendors(List<VendorInfo> toDelete)
    {
        for (VendorInfo v : toDelete)
        {
            // Get Vendor to delete everything from
            Vendor vendorToDelete = vendorRepository.findById(v.getId());

            // Start by removing the files from Vendor
            vendorToDelete.getFiles().forEach(file -> iUploadCenter.deleteFile(file.getId()));

            // Then deleting every user
            vendorToDelete.getUsers().forEach(userRepository::delete);

            // Deleting product infos
            vendorToDelete.getProductInfos().forEach(productInfoRepository::delete);

            // Delete the Vendor Info
            vendorInfoRepository.delete(v);

            // RIP
            vendorRepository.delete(vendorToDelete);
        }
    }

    private void synchronizeVendorProducts(VendorDTO vendorDTO, VendorInfo vendorInfoToUpdate) {
        // Get the vendor based on the given vendorInfo and get its products
        Vendor vendor = vendorRepository.findOne(vendorInfoToUpdate.getId());
        List<ProductInfo> productInfos = vendor.getProductInfos();

        // Find all existing productInfos by comparing IDs
        List<ProductDTO> productUpdateCandidates = vendorDTO.getProdukts().stream().
                filter(theirEntry -> productInfos.stream().
                        anyMatch(ourEntry -> theirEntry.getId().equals(ourEntry.getId()))).
                collect(Collectors.toList());

        // Every productInfo in their list and not in our database needs to be created
        List<ProductDTO> productCreateCandidates = vendorDTO.getProdukts().stream().
                filter(theirEntry -> productUpdateCandidates.stream().
                        anyMatch(ourEntry -> !theirEntry.getId().equals(ourEntry.getId()))).
                collect(Collectors.toList());

        // Every productInfo in our database and not in their list needs to be deleted
        productUpdateCandidates.
                forEach(theirEntry -> productInfos
                        .removeIf(ourEntry -> theirEntry.getId().equals(ourEntry.getId())));
        productInfos.forEach(productInfoRepository::delete);

        // Update and create the rest
        updateProducts(productCreateCandidates);

        // Add every newly created Product to the vendor
        createProducts(productCreateCandidates).forEach(vendor::putProductInfo);
    }

    private List<ProductInfo> createProducts(List<ProductDTO> productCreateCandidates) {
        // we need to return all newly created product infos to save the references in the vendor
        List<ProductInfo> newProductInfos = new ArrayList<>();

        // Create every product info based on given id and label, save it to database and add them to return list
        productCreateCandidates.forEach(entry -> newProductInfos.add(productInfoRepository.save(new ProductInfo(entry.getId(),entry.getLabel()))));

        return  newProductInfos;
    }

    private void updateProducts(List<ProductDTO> productUpdateCandidates) {
        productUpdateCandidates.forEach(entry -> {
            // Find Product Info based on ID an set the name to given Label
            ProductInfo infoToUpdate = productInfoRepository.findOne(entry.getId());
            infoToUpdate.setName(entry.getLabel());

            // Save back to database
            productInfoRepository.save(infoToUpdate);
        });
    }
}