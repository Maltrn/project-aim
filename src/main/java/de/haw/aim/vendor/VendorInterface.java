package de.haw.aim.vendor;

import de.haw.aim.authentication.persistence.User;
import de.haw.aim.vendor.persistence.Vendor;

public interface VendorInterface
{
    /**
     * Returns vendor entity for specific user. If user not known to vendor returns null.
     *
     * @param user User entity
     * @return Vendor entity if user is known to vendor. Null if user not known to vendor
     */
    Vendor getVendor(User user);
}
