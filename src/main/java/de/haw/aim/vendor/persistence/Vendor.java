package de.haw.aim.vendor.persistence;

import de.haw.aim.authentication.persistence.User;
import de.haw.aim.uploadcenter.persistence.UploadedFile;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rene on 31.10.2016.
 */
@Document
public class Vendor
{
    @Id
    private String id;
    @DBRef
    private VendorInfo vendorInfo;
    @DBRef
    private List<ProductInfo> productInfos;
    @DBRef
    private List<User> users;
    @DBRef
    private List<UploadedFile> files;

    private Vendor()
    {

    }
    public Vendor(VendorInfo vendorInfo, List<ProductInfo> productInfos, List<User> users, List<UploadedFile> files)
    {
        this.id = vendorInfo.getId();
        this.vendorInfo = vendorInfo;
        this.productInfos = productInfos;
        this.users = users;
        this.files = files;
    }

    public List<User> getUsers()
    {
        return users;
    }

    public String getVendorInfoId()
    {
        return vendorInfo.getId();
    }

    public List<String> getProdcutInfoIds()
    {
        List<String> retVal = new ArrayList<>();
        for (ProductInfo p : productInfos)
        {
            retVal.add(p.getId());
        }
        return retVal;
    }

    public VendorInfo getVendorInfo()
    {
        return vendorInfo;
    }

    public void setVendorInfo(VendorInfo vendorInfo)
    {
        this.vendorInfo = vendorInfo;
        this.id         = vendorInfo.getId();
    }

    public String getId()
    {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vendor vendor = (Vendor) o;

        return id.equals(vendor.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
