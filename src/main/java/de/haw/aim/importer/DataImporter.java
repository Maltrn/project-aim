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

        List<VendorInfo> updateCandidates =
                vendorInfoRepository.findAll().
                        stream().
                        filter(ourentry -> apiReturn.stream().anyMatch(theirentry -> ourentry.getId().equals(theirentry.getId()))).
                        collect(Collectors.toList());

        List<VendorInfo> toDelete = vendorInfoRepository.findAll();
        toDelete.removeAll(updateCandidates);

        removeVendors(toDelete);
        updateVendors(updateCandidates);

    }

    private void updateVendors(List<VendorInfo> updateCandidates)
    {

    }

    private void removeVendors(List<VendorInfo> toDelete)
    {
        for (VendorInfo v : toDelete)
        {
            // Get Vendor to delete everything from
            Vendor vendorToDelete = vendorRepository.findById(v.getId());

            // Delete MainPic
//            iUploadCenter.deleteFile(v.getMainPic().getId());

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