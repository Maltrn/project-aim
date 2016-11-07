package de.haw.aim.uploadcenter;

import de.haw.aim.uploadcenter.facade.IUploadCenter;
import de.haw.aim.uploadcenter.persistence.*;
import de.haw.aim.validator.ValueDoesntValidateToConfigFileException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private PDFRepository pdfRepository;

    @Autowired
    private PictureRepository pictureRepository;

    private Path uploadedFilesLocation;

    private final Log logger = LogFactory.getLog(getClass());

    public UploadCenter(@Value("${uploadcenter.fileslocation}") String fileLocationFolder) {
        this.uploadedFilesLocation = Paths.get(fileLocationFolder);
    }

    @Override
    public UploadedFile uploadFile(MultipartFile f) throws StorageException {
        if (f == null || f.isEmpty()) {
            throw new StorageException("Failed to store empty file");
        }

        try {
            Files.copy(f.getInputStream(), uploadedFilesLocation.resolve(f.getOriginalFilename()));
        } catch (IOException e) {
            throw new StorageException("File could not be stored locally", e);
        }

        String fileLocation = uploadedFilesLocation.resolve(f.getOriginalFilename()).toString();
        UploadedFile result = null;
        MongoRepository repository = null;

        // Try creating a valid PDF first
        try {
            PDF pdf = new PDF(fileLocation);
            pdf.validate();
            result = pdf;
            repository = this.pdfRepository;
        } catch (ValueDoesntValidateToConfigFileException e) {
            logger.info("'" + fileLocation + "' was not a valid pdf");
        }

        // Try creating a valid Picture next
        try {
            Picture pic = new Picture(fileLocation);
            pic.validate();
            result = pic;
            repository = this.pictureRepository;
        } catch (ValueDoesntValidateToConfigFileException e) {
            logger.info("'" + fileLocation + "' was not a valid picture");
        }

        if (repository == null) {
            throw new StorageException("The filetype of the uploaded file is invalid");
        }

        result = (UploadedFile) repository.save(result);
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
