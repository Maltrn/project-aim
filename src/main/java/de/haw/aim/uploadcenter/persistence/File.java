package de.haw.aim.uploadcenter.persistence;

import de.haw.aim.validator.Validatable;

public interface File extends Validatable
{
    public String getName();

    public String getLocation();

    public String getId();
}
