package de.haw.aim.uploadcenter;

import de.haw.aim.uploadcenter.facade.IUploadCenter;
import de.haw.aim.uploadcenter.persistence.File;

/**
 * Created by Jana Mareike on 07.11.2016.
 */
public class UploadCenter implements IUploadCenter
{
    @Override
    public File uploadFile(File f)
    {

        return null;
    }

    @Override
    public File replaceFile(String id, File f)
    {
        return null;
    }

    @Override
    public boolean deleteFile(String id)
    {
        return false;
    }

    @Override
    public boolean checkForExistence(String id)
    {
        return false;
    }
}
