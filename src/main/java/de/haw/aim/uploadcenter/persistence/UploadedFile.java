package de.haw.aim.uploadcenter.persistence;

import de.haw.aim.validator.Validatable;

public interface UploadedFile extends Validatable
{
    public String getName();

    public String getLocation();

    public String getId();
}
