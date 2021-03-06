package de.haw.aim.vendor.facade;

import de.haw.aim.authentication.persistence.User;
import de.haw.aim.vendor.persistence.ProductInfo;
import de.haw.aim.vendor.persistence.Vendor;
import de.haw.aim.vendor.persistence.VendorInfo;

import java.util.List;

public interface IVendor
{
    /**
     * Returns first vendor entity found  for specific user.json. If user.json not known from vendor returns null.
     * TODO: Always returns first vendor entity found! Can't handle users associated with multiple vendors.
     *
     * @param user User entity
     * @return Vendor entity if user.json is known from vendor. Null if user.json not known from vendor
     */
    Vendor getVendor(User user);

    /**
     * Returns a list of vendors.
     * @return List<Vendor>, empty if no vendors available.
     */
    List<Vendor> getVendors();

    /**
     * Returns a specific vendor based on the given ID.
     * @param id - Vendor ID from look for
     * @return Vendor, based on ID, null if no vendor found
     */
    Vendor getVendor(String id);

    /**
     * Tries to save a VendorInfo object to the DB. Only updates existing VendorInfo objects.
     * @param vendorInfo VendorInfo to save
     * @return Updated Vendor entity for further referencing. Returns null if VendorInfo didn't exist in DB.
     */
    Vendor putVendor(VendorInfo vendorInfo);

    /**
     * Returns a list of Product Infos
     * @return List<ProductInfo>, empty if no Product Infos available.
     */
    List<ProductInfo> getProducts();

    /**
     * Returns a specific Product Info based on the given ID.
     * @param id - Product Info ID from look for
     * @return Product Info, based on ID, null if no Product Info found
     */
    ProductInfo getProduct(String id);

    /**
     * Saves a productInfo to the database. Used for updating existing prodcuts.
     * @param productInfo productInfo to save to database
     * @return Updated productInfo for further reference. Has to return null if product not already existing
     * in database, to prevent creating new Products
     */
    ProductInfo saveProduct(ProductInfo productInfo);

    /**
     * Tries to save a vendor object to the DB. Only updates existing vendor objects.
     * @param vendor Vendor to save
     * @return Updated Vendor entity for further referencing. Returns null if Vendor didn't exist in DB.
     */
    Vendor saveVendor(Vendor vendor);

}
