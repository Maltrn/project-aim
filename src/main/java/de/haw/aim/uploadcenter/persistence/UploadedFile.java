package de.haw.aim.uploadcenter.persistence;

import de.haw.aim.validator.Validatable;

public interface UploadedFile extends Validatable
{
    String getName();

    String getLocation();

    String getId();

    String getVendorId();
}
