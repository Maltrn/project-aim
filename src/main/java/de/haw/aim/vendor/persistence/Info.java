package de.haw.aim.vendor.persistence;


import de.haw.aim.uploadcenter.persistence.File;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Rene on 31.10.2016.
 */
@Document
public abstract class Info {
    @Id
    private String id;
    private String name;
    private String shortDescription;
    private String longDescription;
    @DBRef
    private Picture mainPic;
    @DBRef
    private List<File> fileGallery;
    private List<Fact> facts;

    Info(String name, String shortDescription, String longDescription, Picture mainPic, List<File> fileGallery, List<Fact> facts) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.mainPic = mainPic;
        this.fileGallery = fileGallery;
        this.facts = facts;
    }

    public String getId() {
        return id;
    }
}
