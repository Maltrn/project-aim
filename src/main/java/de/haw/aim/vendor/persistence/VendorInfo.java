package de.haw.aim.vendor.persistence;

import de.haw.aim.validator.Validatable;
import de.haw.aim.validator.ValueDoesntValidateToConfigFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class VendorInfo extends Info implements Validatable{

    @Autowired
    Environment env;

    @Override
    public void validate() throws ValueDoesntValidateToConfigFileException
    {
        if (this.getShortDescription().length() > Integer.valueOf(env.getProperty("vendorinfo.shortdescription.maxlength")))
        {
            throw new ValueDoesntValidateToConfigFileException("short description is too long");
        }
        if (this.getLongDescription().length() > Integer.valueOf(env.getProperty("vendorinfo.longdescription.maxlength")))
        {
            throw new ValueDoesntValidateToConfigFileException("long description is too long");
        }

    }
}
