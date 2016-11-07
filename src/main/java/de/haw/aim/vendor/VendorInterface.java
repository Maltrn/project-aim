package de.haw.aim.vendor;

import de.haw.aim.authentication.persistence.User;
import de.haw.aim.vendor.persistence.Vendor;
import de.haw.aim.vendor.persistence.VendorInfo;

import java.util.List;

public interface VendorInterface
{
    /**
     * Returns vendor entity for specific user. If user not known from vendor returns null.
     *
     * @param user User entity
     * @return Vendor entity if user is known from vendor. Null if user not known from vendor
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
     * @return true if ID already exists and object was saved, false if ID is not present in DB
     */
    boolean putVendor(VendorInfo vendorInfo);
}
