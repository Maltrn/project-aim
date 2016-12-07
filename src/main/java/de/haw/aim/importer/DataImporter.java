package de.haw.aim.importer;


import de.haw.aim.authentication.persistence.UserRepository;
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
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataImporter
{
    private static final String urlToCall = "http://132.252.58.189:8080/ontology/vendors";
    @Autowired
    VendorInfoRepository vendorInfoRepository;
    @Autowired
    VendorRepository vendorRepository;
    @Autowired
    ProductInfoRepository productInfoRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    IUploadCenter iUploadCenter;


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

    private void persistData() throws ServiceUnavailableException
    {
        List<VendorDTO> apiReturn = getData();

        // Find entries which mach to api ids, these need to be updated
        List<VendorDTO> updateCandidates =
                        apiReturn.
                        stream().
                        filter(theirentry -> vendorInfoRepository.
                                findAll().
                                stream().
                                anyMatch(ourentry -> theirentry.getId().equals(ourentry.getId()))).
                        collect(Collectors.toList());

        // every entry on apiReturn and not on updateCandidates needs to be created
        List<VendorDTO> createCandidates =
                        apiReturn.
                        stream().
                        filter(theirentry -> updateCandidates.stream().anyMatch(ourentry -> !theirentry.getId().equals(ourentry.getId()))).
                        collect(Collectors.toList());

        // every entry which
        List<VendorInfo> toDelete = vendorInfoRepository.findAll();
        toDelete.removeAll(updateCandidates);

        removeVendors(toDelete);
        updateVendors(updateCandidates);
        createVendor(createCandidates);

    }

    private void createVendor(List<VendorDTO> createCandidates)
    {
        for (VendorDTO v : createCandidates)
        {
            
        }
    }

    private void updateVendors(List<VendorDTO> updateCandidates)
    {
        for (VendorDTO v : updateCandidates)
        {
            VendorInfo toUpdate = vendorInfoRepository.findOne(v.getId());
            toUpdate.setName(v.getLabel());
            
            for(ProductInfo p : vendorRepository.findById(v.getId()).getProductInfos()){
//                for (:
//                     )
//                {
//
//                }
            }
            
            vendorInfoRepository.save(toUpdate);
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

    public void synchronize() throws ServiceUnavailableException
    {
        persistData();
    }

}