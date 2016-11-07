package de.haw.aim.vendor.persistence;


import de.haw.aim.uploadcenter.persistence.File;
import de.haw.aim.uploadcenter.persistence.Picture;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Rene on 31.10.2016.
 */
@Document
public abstract class Info
{
    @Id
    protected String id;
    protected String name;
    protected String shortDescription;
    protected String longDescription;
    @DBRef
    protected Picture mainPic;
    protected List<File> fileGallery;
    protected List<Fact> facts;

    public Info()
    {

    }

    public Info(String name, String shortDescription, String longDescription, Picture mainPic, List<File> fileGallery, List<Fact> facts)
    {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.mainPic = mainPic;
        this.fileGallery = fileGallery;
        this.facts = facts;
    }

    public String getId()
    {
        return id;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Info)) return false;

        Info info = (Info) o;

        return id.equals(info.id);

    }

    @Override
    public int hashCode()
    {
        return id.hashCode();
    }
}
