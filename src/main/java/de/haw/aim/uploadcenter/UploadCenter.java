package de.haw.aim.uploadcenter;

import de.haw.aim.uploadcenter.facade.IUploadCenter;
import de.haw.aim.uploadcenter.persistence.File;
import de.haw.aim.uploadcenter.persistence.PDF;
import de.haw.aim.uploadcenter.persistence.Picture;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UploadCenter implements IUploadCenter
{
    private Path fileUploadFolder;

    public UploadCenter(String fileUploadFolder)
    {
        this.fileUploadFolder = Paths.get(fileUploadFolder);
    }

    @Override
    public File uploadFile(MultipartFile f) throws IOException, StorageException
    {
        if (f.isEmpty())
        {
            throw new StorageException("Failed to store empty file " + f.getOriginalFilename());
        }

        Files.copy(f.getInputStream(), this.fileUploadFolder.resolve(f.getOriginalFilename()));
        String fileLocation = this.fileUploadFolder.resolve(f.getOriginalFilename()).toString();
        File result;

        if (f.getOriginalFilename().endsWith(".pdf"))
        {
            result = new PDF(fileLocation);
        } else
        {
            result = new Picture(fileLocation);
        }
        return result;
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

    @Override
    public File findById(String id)
    {
        return null;
    }
}
