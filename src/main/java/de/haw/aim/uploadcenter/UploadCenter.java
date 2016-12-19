package de.haw.aim.uploadcenter;

import de.haw.aim.uploadcenter.facade.IUploadCenter;
import de.haw.aim.uploadcenter.persistence.*;
import de.haw.aim.validator.ValueDoesntValidateToConfigFileException;
import de.haw.aim.vendor.VendorComponent;
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

    @Autowired
    private VendorComponent vendorComponent;

    private final Path uploadedFilesLocation;

    private final Log logger = LogFactory.getLog(getClass());

    public UploadCenter(@Value("${uploadcenter.fileslocation}") String fileLocationFolder) {
        this.uploadedFilesLocation = Paths.get(fileLocationFolder);
    }

    @Override
    public UploadedFile uploadFile(MultipartFile f, String vendorId) throws StorageException {
        Path filePath = uploadedFilesLocation.resolve(vendorId + File.separator + f.getOriginalFilename().toString());

        new File(uploadedFilesLocation.resolve(vendorId).toString()).mkdir();

        if (f == null || f.isEmpty()) {
            throw new StorageException("Failed to store empty file");
        }

        try {
            Files.copy(f.getInputStream(), filePath);
        } catch (IOException e) {
            throw new StorageException("File could not be stored locally", e);
        }

        String fileLocation = vendorId + File.separator + f.getOriginalFilename().toString();
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
    public UploadedFile replaceFile(String id, MultipartFile f) throws IOException
    {
        if (this.findById(id) == null || f == null) {
            throw new StorageException("File does not exist");
        }

        UploadedFile foundFile = this.findById(id);
        UploadedFile uploadedFile = this.uploadFile(f, foundFile.getVendorId());
        MongoRepository repository = this.pdfRepository;
        UploadedFile replacedFile;

        if (foundFile.getClass() == PDF.class) {
            PDF file = new PDF(uploadedFile.getLocation());
            file.setId(id);
            replacedFile = file;
        } else if (foundFile.getClass() == Picture.class) {
            Picture file = new Picture(uploadedFile.getLocation());
            repository = this.pictureRepository;
            file.setId(id);
            replacedFile = file;
        } else {
            throw new StorageException("Could not determine class of uploaded file!");
        }

        repository.save(replacedFile);
        repository.delete(uploadedFile.getId());
        try {
            Files.delete(Paths.get(foundFile.getLocation()));
        } catch (IOException e) {
            logger.info("Tried to delete non existent file");
        }
        return replacedFile;
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
        UploadedFile uploadedFile = this.findById(id);
        return (uploadedFile != null) && (new File(uploadedFile.getLocation()).exists());
    }

    @Override
    public UploadedFile findById(String id) {
        if (this.pdfRepository.findOne(id) != null) {
            return this.pdfRepository.findOne(id);
        } else if (this.pictureRepository.findOne(id) != null) {
            return this.pictureRepository.findOne(id);
        } else {
            return null;
        }
    }
}
