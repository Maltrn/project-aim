package de.haw.aim.vendor.persistence;

import de.haw.aim.uploadcenter.persistence.File;
import de.haw.aim.uploadcenter.persistence.Picture;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class VendorInfo extends Info
{

    public VendorInfo(String name, String shortDescription, String longDescription, Picture mainPic, List<File> fileGallery, List<Fact> facts)
    {
        super(name, shortDescription, longDescription, mainPic, fileGallery, facts);
    }

}
