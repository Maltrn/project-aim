package de.haw.aim.uploadcenter.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PDF implements File {

    @Id
    protected String id;

    protected String name;

    protected java.io.File file;

    public PDF() {
    }

    public PDF(String filepath) {
        this.file = new java.io.File(filepath);
        this.name = this.file.getName().substring(0, this.file.getName().length() - 4);
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

    @Override
    public boolean isValid() {
        return this.getFile().getName().endsWith(".pdf");
    }
}
