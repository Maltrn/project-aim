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
    String id;
    String name;
    String shortDescription;
    String longDescription;
    @DBRef
    Picture mainPic;
    @DBRef
    List<File> fileGallery;
    List<Fact> facts;
}
