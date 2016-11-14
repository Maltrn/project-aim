package de.haw.aim.vendor;

import de.haw.aim.authentication.persistence.User;
import de.haw.aim.vendor.facade.IVendor;
import de.haw.aim.vendor.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VendorComponent implements IVendor
{

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private ProductInfoRepository productInfoRepository;

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
    public Vendor putVendor(VendorInfo vendorInfo)
    {
        Vendor vendor = vendorRepository.findById(vendorInfo.getId());
        if(vendor == null)
            return vendor;
        vendor.setVendorInfo(vendorInfo);
        return vendorRepository.save(vendor);
    }

    @Override
    public List<ProductInfo> getProducts()
    {
        return productInfoRepository.findAll();
    }

    @Override
    public ProductInfo getProduct(String id)
    {
        return productInfoRepository.findById(id);
    }

    @Override
    public Vendor saveVendor(Vendor vendor)
    {
        return vendorRepository.save(vendor);
    }


}
