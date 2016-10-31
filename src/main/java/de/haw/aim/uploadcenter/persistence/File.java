package de.haw.aim.uploadcenter.persistence;

import de.haw.aim.validator.Validatable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class File implements Validatable {

    @Id
    protected String id;

    protected String name;

    protected String type;

    protected java.io.File file;

    public File() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.io.File getFile() {
        return file;
    }

    public void setFile(java.io.File file) {
        this.file = file;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
