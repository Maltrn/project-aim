package de.haw.aim.uploadcenter;

import de.haw.aim.uploadcenter.facade.IUploadCenter;
import de.haw.aim.uploadcenter.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class UploadCenter implements IUploadCenter
{
    @Autowired
    private Environment environment;

    @Autowired
    private PDFRepository pdfRepository;

    @Autowired
    private PictureRepository pictureRepository;

    public UploadCenter()
    {

    }

    @Override
    public File uploadFile(MultipartFile f) throws IOException, StorageException
    {
        if (f.isEmpty())
        {
            throw new StorageException("Failed to store empty file " + f.getOriginalFilename());
        }

        Path uploadFolderLocation = Paths.get(this.environment.getProperty("uploadcenter.fileslocation"));

        Files.copy(f.getInputStream(), uploadFolderLocation.resolve(f.getOriginalFilename()));
        String fileLocation = uploadFolderLocation.resolve(f.getOriginalFilename()).toString();
        File result;

        if (f.getOriginalFilename().endsWith(".pdf"))
        {
            result = this.pdfRepository.save(new PDF(fileLocation));
        } else
        {
            result = this.pictureRepository.save(new Picture(fileLocation));
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
