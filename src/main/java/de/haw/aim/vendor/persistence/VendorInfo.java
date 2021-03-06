package de.haw.aim.vendor.persistence;

import de.haw.aim.uploadcenter.persistence.Picture;
import de.haw.aim.uploadcenter.persistence.UploadedFile;

import java.util.ArrayList;
import java.util.List;

public class VendorInfo extends Info
{

    public VendorInfo(String id, String name, String shortDescription, String longDescription, Picture mainPic, List<UploadedFile> fileGallery, List<Fact> facts)
    {
        super(id, name, shortDescription, longDescription, mainPic, fileGallery, facts);
    }
    public VendorInfo(String name, String shortDescription, String longDescription, Picture mainPic, List<UploadedFile> fileGallery, List<Fact> facts)
    {
        super(name, shortDescription, longDescription, mainPic, fileGallery, facts);
    }
    public VendorInfo(String id, String name)
    {
        this(id, name, "", "", null, new ArrayList<>(), new ArrayList<>());
    }

    public VendorInfo()
    {
        super();
    }


}
