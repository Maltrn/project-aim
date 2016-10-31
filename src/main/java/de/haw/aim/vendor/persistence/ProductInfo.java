package de.haw.aim.vendor.persistence;

import de.haw.aim.uploadcenter.persistence.File;
import de.haw.aim.uploadcenter.persistence.Picture;

import java.util.List;

/**
 * Created by Rene on 31.10.2016.
 */
public class ProductInfo extends Info {
    public ProductInfo(String name, String shortDescription, String longDescription, Picture mainPic, List<File> fileGallery, java.util.List<Fact> facts)
    {
        super(name, shortDescription, longDescription, mainPic, fileGallery, facts);
    }
}
