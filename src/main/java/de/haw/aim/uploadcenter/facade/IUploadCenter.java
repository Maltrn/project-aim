package de.haw.aim.uploadcenter.facade;

import de.haw.aim.uploadcenter.persistence.File;

public interface IUploadCenter
{
    public File uploadFile(File f);

    public File replaceFile(String id, File f);

    public boolean deleteFile(String id);

    public boolean checkForExistence(String id);
}
