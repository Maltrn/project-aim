package de.haw.aim.vendor;

import de.haw.aim.authentication.persistence.User;
import de.haw.aim.vendor.persistence.Vendor;
import de.haw.aim.vendor.persistence.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Rene on 31.10.2016.
 */
public class VendorComponent implements VendorInterface {

    private VendorRepository vendorRepository;

    @Autowired
    public VendorComponent(VendorRepository vendorRepository){
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Vendor getVendor(User user) {
        for (Vendor vendor : vendorRepository.findAll()){
            if(vendor.getUsers().contains(user)){
                return vendor;
            }
        }
        return null;
    }
}
