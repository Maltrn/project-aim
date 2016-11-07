package de.haw.aim.uploadcenter.facade;

import de.haw.aim.uploadcenter.persistence.UploadedFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IUploadCenter
{
    public UploadedFile uploadFile(MultipartFile f) throws IOException;

    public UploadedFile replaceFile(String id, UploadedFile f);

    public boolean deleteFile(String id);

    public boolean checkForExistence(String id);

    public UploadedFile findById(String id);


}
