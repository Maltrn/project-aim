package de.haw.aim.uploadcenter.facade;

import de.haw.aim.uploadcenter.persistence.UploadedFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IUploadCenter
{
    UploadedFile uploadFile(MultipartFile f) throws IOException;

    UploadedFile replaceFile(String id, MultipartFile f);

    boolean deleteFile(String id);

    boolean checkForExistence(String id);

    UploadedFile findById(String id);
}
