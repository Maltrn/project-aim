package de.haw.aim.uploadcenter;

import de.haw.aim.uploadcenter.facade.IUploadCenter;
import de.haw.aim.uploadcenter.persistence.*;
import de.haw.aim.validator.ValueDoesntValidateToConfigFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class UploadCenter implements IUploadCenter {
    @Autowired
    private Environment environment;

    @Autowired
    private PDFRepository pdfRepository;

    @Autowired
    private PictureRepository pictureRepository;

    public UploadCenter() {

    }

    @Override
    public UploadedFile uploadFile(MultipartFile f) throws IOException, StorageException {
        if (f.isEmpty()) {
            throw new StorageException("Failed to store empty file " + f.getOriginalFilename());
        }

        Path uploadFolderLocation = Paths.get(this.environment.getProperty("uploadcenter.fileslocation"));

        Files.copy(f.getInputStream(), uploadFolderLocation.resolve(f.getOriginalFilename()));
        String fileLocation = uploadFolderLocation.resolve(f.getOriginalFilename()).toString();
        UploadedFile result;

        if (f.getOriginalFilename().endsWith("." + this.environment.getProperty("uploadcenter.pdf.filetypes"))) {
            PDF pdf = new PDF(fileLocation);
            try {
                pdf.validate();
                result = this.pdfRepository.save(pdf);
            } catch (ValueDoesntValidateToConfigFileException e) {
                throw new StorageException("Filetype not supported!");
            }
        } else {
            Picture picture = new Picture(fileLocation);
            try {
                picture.validate();
                result = this.pictureRepository.save(picture);
            } catch (ValueDoesntValidateToConfigFileException e) {
                throw new StorageException("Filetype not supported!");
            }
        }
        return result;
    }

    @Override
    public UploadedFile replaceFile(String id, UploadedFile f) {
        return null;
    }

    @Override
    public boolean deleteFile(String id) {
        MongoRepository repo = this.pdfRepository;
        UploadedFile dbFile = this.pdfRepository.findOne(id);
        if (dbFile == null) {
            dbFile = this.pictureRepository.findOne(id);
            repo = this.pictureRepository;
        }

        if (dbFile != null) {
            File physicalFile = new File(dbFile.getLocation());
            repo.delete(dbFile.getId());
            return physicalFile.delete();
        }

        return false;
    }

    @Override
    public boolean checkForExistence(String id) {
        return false;
    }

    @Override
    public UploadedFile findById(String id) {
        return null;
    }
}
