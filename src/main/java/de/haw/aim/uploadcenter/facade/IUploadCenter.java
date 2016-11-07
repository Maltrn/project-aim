package de.haw.aim.uploadcenter.facade;

import de.haw.aim.uploadcenter.persistence.File;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadCenter
{
    public File uploadFile(MultipartFile f);

    public File replaceFile(String id, File f);

    public boolean deleteFile(String id);

    public boolean checkForExistence(String id);

    public File findById(String id);


}
