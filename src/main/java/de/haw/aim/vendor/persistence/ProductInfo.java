package de.haw.aim.vendor.persistence;

import de.haw.aim.uploadcenter.persistence.Picture;
import de.haw.aim.uploadcenter.persistence.UploadedFile;

import java.util.ArrayList;
import java.util.List;

public class ProductInfo extends Info
{
    public ProductInfo(String id, String name, String shortDescription, String longDescription, Picture mainPic, List<UploadedFile> fileGallery, java.util.List<Fact> facts)
    {
        super(id, name, shortDescription, longDescription, mainPic, fileGallery, facts);
    }
    public ProductInfo(String name, String shortDescription, String longDescription, Picture mainPic, List<UploadedFile> fileGallery, java.util.List<Fact> facts)
    {
        super(name, shortDescription, longDescription, mainPic, fileGallery, facts);
    }
    public ProductInfo(String id, String name)
    {
        this(id, name, "", "", null, new ArrayList<>(), new ArrayList<>());
    }
    public ProductInfo()
    {
        super();
    }


}
