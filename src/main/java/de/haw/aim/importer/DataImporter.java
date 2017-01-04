package de.haw.aim.importer;


import de.haw.aim.authentication.persistence.User;
import de.haw.aim.authentication.persistence.UserRepository;
import de.haw.aim.importer.dto.ProductDTO;
import de.haw.aim.importer.dto.VendorDTO;
import de.haw.aim.uploadcenter.facade.IUploadCenter;
import de.haw.aim.vendor.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.naming.ServiceUnavailableException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class DataImporter
{

    //####################################################################
    //FIXME just for creating dummy users to login with
    //####################################################################
    private static final String ALPHABET = "abcdewfghijklmopqrstuvwxyz";
    private static final int N = ALPHABET.length();
    private static final int USERNAMELENGTH = 15;
    private static final String PASSWORD = "thisisapassword";
    private final Random r = new Random();
    //####################################################################

    //####################################################################
    //TODO extract to config file
    //####################################################################
    private static final String urlToCall = "http://132.252.58.189:8080/ontology/vendors";
    public static final int SYNCHRONIZERATE = 1800000;
    //####################################################################

    private final Logger logger = LoggerFactory.getLogger(DataImporter.class);

    private VendorInfoRepository vendorInfoRepository;

    private VendorRepository vendorRepository;

    private ProductInfoRepository productInfoRepository;

    private UserRepository userRepository;

    private IUploadCenter iUploadCenter;

    @Autowired
    public DataImporter(VendorInfoRepository vendorInfoRepository, VendorRepository vendorRepository, ProductInfoRepository productInfoRepository, UserRepository userRepository, IUploadCenter iUploadCenter)
    {
        this();
        this.vendorInfoRepository = vendorInfoRepository;
        this.vendorRepository = vendorRepository;
        this.productInfoRepository = productInfoRepository;
        this.userRepository = userRepository;
        this.iUploadCenter = iUploadCenter;
    }

    private final RestTemplate restTemplate;

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
            logger.error("Synchronize failed, Symphony is not responding");
            throw new ServiceUnavailableException();
        }

        return exchange.getBody();
    }

    // This method is called on application start, every 30 Minutes and if a user logs in
    @Scheduled(fixedRate = SYNCHRONIZERATE)
    public void synchronize() throws ServiceUnavailableException
    {
        logger.info("Synchronize from " + urlToCall + " started");

        // Get all the data from symphony api
        List<VendorDTO> apiReturn = getData();

        // Get all vendorinfos from our database
        List<String> vendorInfos = vendorInfoRepository.findAll().stream().map(VendorInfo::getId).collect(Collectors.toList());

        // Find entries which mach to api ids, these need to be updated
        List<String> vendorUpdateCandidates = apiReturn.
                        stream().
                        map(VendorDTO::getId).
                        filter(theirId -> vendorInfos.
                                stream().
                                anyMatch(theirId::equals)).
                        collect(Collectors.toList());

        // every entry on apiReturn and not on vendorUpdateCandidates needs to be created
        List<String> vendorCreateCandidates = new ArrayList<>();
        if (vendorInfos.isEmpty())
        {
            vendorCreateCandidates.addAll(apiReturn.stream().map(VendorDTO::getId).collect(Collectors.toList()));
        } else
        {
            vendorCreateCandidates = apiReturn.
                            stream().
                            map(VendorDTO::getId).
                            filter(theirId -> vendorInfos.
                                    stream().
                                    noneMatch(theirId::equals)).
                            collect(Collectors.toList());
        }

        // every entry which are in our database and not in vendorUpdateCandidates need to be deleted
        List<String> ourIds = new ArrayList<>();
        vendorUpdateCandidates.forEach(ourIds::add);

        List<String> theirIds = new ArrayList<>();
        vendorInfos.forEach(theirIds::add);

        ourIds.removeAll(theirIds);

        removeVendors(ourIds);
        updateVendors(apiReturn,vendorUpdateCandidates);
        createVendor(apiReturn,vendorCreateCandidates);

        logger.info("Synchronization completed");
    }

    private void createVendor(List<VendorDTO> apiReturn, List<String> vendorCreateCandidatesId)
    {
        apiReturn.stream().filter(entry -> vendorCreateCandidatesId.contains(entry.getId())).forEach(vendorDTO -> {
            //####################################################################
            //FIXME just for creating dummy users to login with
            //####################################################################
            String currentUsername = "";
            for (int i = 0; i <= USERNAMELENGTH; i++)
            {
                currentUsername += ALPHABET.charAt(r.nextInt(N));
            }
            currentUsername += "@aim.de";

            User currentUser = new User(currentUsername,PASSWORD);
            userRepository.save(currentUser);

            logger.info("####################DUMMY USER###################");
            logger.info("user: " + currentUsername);
            logger.info("vendor: " + vendorDTO.getLabel());
            logger.info("#################################################");
            //####################################################################

            // Create the vendorInfo entity
            VendorInfo vendorInfoToCreate = new VendorInfo(vendorDTO.getId(), vendorDTO.getLabel());
            vendorInfoToCreate = vendorInfoRepository.save(vendorInfoToCreate);

            // For a new vendor every product has to be new, so we only need to create products for this
            List<ProductInfo> productInfos = createProducts(vendorDTO.getProdukts());

            // Finally create the new vendor which brings productinfos and vendorinfo together
            Vendor vendor = new Vendor(vendorInfoToCreate, productInfos);

            //####################################################################
            //FIXME just for creating dummy users to login with
            //####################################################################
            vendor.addUser(currentUser);
            //####################################################################

            vendorRepository.save(vendor);
        });
    }

    private void updateVendors(List<VendorDTO> apiReturn, List<String> vendorUpdateCandidatesId)
    {
        // for every updateCandidate 
        apiReturn.stream().filter(entry -> vendorUpdateCandidatesId.contains(entry.getId())).forEach(vendorDTO -> {
            // find the vendorInfo entity in database based on the ID
            VendorInfo vendorInfoToUpdate = vendorInfoRepository.findOne(vendorDTO.getId());

            // set the name to the new name
            vendorInfoToUpdate.setName(vendorDTO.getLabel());

            // synchronize every product for this vendor
            synchronizeVendorProducts(vendorDTO, vendorInfoToUpdate);

            // save the updated vendorInfo entity to database
            vendorInfoRepository.save(vendorInfoToUpdate);
        });
    }

    private void removeVendors(List<String> toDelete)
    {
        for (String vendorId : toDelete)
        {
            // Get Vendor to delete everything from
            Vendor vendorToDelete = vendorRepository.findById(vendorId);

            // Start by removing the files from Vendor
            vendorToDelete.getFiles().forEach(file -> iUploadCenter.deleteFile(file.getId()));

            // Then deleting every user.json
            vendorToDelete.getUsers().forEach(userRepository::delete);

            // Deleting product infos
            vendorToDelete.getProductInfos().forEach(productInfoRepository::delete);

            // Delete the Vendor Info
            vendorInfoRepository.delete(vendorId);

            // RIP
            vendorRepository.delete(vendorToDelete);
        }
    }

    private void synchronizeVendorProducts(VendorDTO vendorDTO, VendorInfo vendorInfoToUpdate)
    {
        // Get the vendor based on the given vendorInfo and get its products
        Vendor vendor = vendorRepository.findOne(vendorInfoToUpdate.getId());
        List<String> productInfoIds = vendor.getProductInfos().stream().map(ProductInfo::getId).collect(Collectors.toList());

        List<ProductDTO> productDTOS = vendorDTO.getProdukts();

        // Find all existing productInfos by comparing IDs
        List<String> productUpdateCandidateIds = productDTOS.stream().
                map(ProductDTO::getId).
                filter(theirId -> productInfoIds.
                        stream().
                        anyMatch(theirId::equals)).
                collect(Collectors.toList());

        // Every productInfo in their list and not in our database needs to be created
        List<String> productCreateCandidateIds = new ArrayList<>();

        if(productInfoIds.isEmpty()){
            productCreateCandidateIds.addAll(productDTOS.stream().map(ProductDTO::getId).collect(Collectors.toList()));
        }
        else {
            productCreateCandidateIds = productDTOS.stream().
                    map(ProductDTO::getId).
                    filter(theirId -> productInfoIds.
                            stream().
                            noneMatch(theirId::equals)).
                    collect(Collectors.toList());
        }

        // Every productInfo in our database and not in their list needs to be deleted
        List<String> ourIds = new ArrayList<>();
        productUpdateCandidateIds.forEach(ourIds::add);

        List<String> theirIds = new ArrayList<>();
        productInfoIds.forEach(theirIds::add);

        ourIds.removeAll(theirIds);

        ourIds.forEach(productInfoRepository::delete);

        // Update and create the rest
        updateProducts(productDTOS,productUpdateCandidateIds);

        // Add every newly created Product to the vendor
        createProducts(productDTOS,productCreateCandidateIds).forEach(vendor::putProductInfo);
    }

    /**
     * Creates ProductInfo based on product list, every item will be create
     * @param products List of ProductDTO
     * @return List of ProductInfo
     */
    private List<ProductInfo> createProducts(List<ProductDTO> products)
    {
        return createProducts(products,products.stream().map(ProductDTO::getId).collect(Collectors.toList()));
    }

    /**
     * Creates ProductInfo based on product list, only item with ids provided with productCreateCandidateIds will be created
     * @param products List of ProductDTO
     * @param productCreateCandidateIds IDs of Products to be created
     * @return List of ProductInfo
     */
    private List<ProductInfo> createProducts(List<ProductDTO> products, List<String> productCreateCandidateIds)
    {

        // we need to return all newly created product infos to save the references in the vendor
        List<ProductInfo> newProductInfos = new ArrayList<>();

        // Create every product info based on given id and label, save it to database and add them to return list
        products.stream().filter(entry -> productCreateCandidateIds.contains(entry.getId())).forEach(entry ->
                newProductInfos.add(productInfoRepository.save(new ProductInfo(entry.getId(), entry.getLabel()))));

        return newProductInfos;
    }

    private void updateProducts(List<ProductDTO> products, List<String> productUpdateCandidateIds)
    {
        products.stream().filter(entry -> productUpdateCandidateIds.contains(entry.getId())).forEach(entry ->
        {
            // Find Product Info based on ID an set the name to given Label
            ProductInfo infoToUpdate = productInfoRepository.findOne(entry.getId());
            infoToUpdate.setName(entry.getLabel());

            // Save back to database
            productInfoRepository.save(infoToUpdate);
        });
    }
}