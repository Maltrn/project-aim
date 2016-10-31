package de.haw.aim.uploadcenter.persistence;

import de.haw.aim.validator.Validatable;

public interface File extends Validatable {
    public String getName();

    public java.io.File getFile();

    public String getId();
}
