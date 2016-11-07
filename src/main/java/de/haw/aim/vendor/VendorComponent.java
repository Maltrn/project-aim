package de.haw.aim.vendor;

import de.haw.aim.authentication.persistence.User;
import de.haw.aim.vendor.persistence.Vendor;
import de.haw.aim.vendor.persistence.VendorInfo;
import de.haw.aim.vendor.persistence.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VendorComponent implements VendorInterface
{

    private VendorRepository vendorRepository;

    @Autowired
    public VendorComponent(VendorRepository vendorRepository)
    {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Vendor getVendor(User user)
    {
        for (Vendor vendor : vendorRepository.findAll())
        {
            if (vendor.getUsers().contains(user))
            {
                return vendor;
            }
        }
        return null;
    }

    @Override
    public List<Vendor> getVendors()
    {
        return vendorRepository.findAll();
    }

    @Override
    public Vendor getVendor(String id)
    {
        return vendorRepository.findById(id);
    }

    @Override
    public boolean putVendor(VendorInfo vendorInfo)
    {
        Vendor vendor = vendorRepository.findById(vendorInfo.getId());
        if(vendor == null)
            return false;
        vendor.setVendorInfo(vendorInfo);
        vendorRepository.save(vendor);
        return true;
    }


}
