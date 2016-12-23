package de.haw.aim.uploadcenter.facade;

import de.haw.aim.uploadcenter.persistence.UploadedFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IUploadCenter
{
    /**
     * Tries to save file to local filesystem and entity to the database
     * @param f MultipartFile to save
     * @return Returns UploadedFile entity with ID
     * @throws IOException if File Type is not valid, the file is empty, or file could not be stored locally
     */
    UploadedFile uploadFile(MultipartFile f, String vendorId) throws IOException;

    /**
     * Tries to replace a file with given ID
     * @param id ID of file to be replaced
     * @param f updated MultipartFile
     * @return Returns updated UploadedFile entity with new ID
     * @throws IOException if File does not exist in database, file type is not valid, not able to replace file
     */
    UploadedFile replaceFile(String id, MultipartFile f) throws IOException;

    /**
     * Tries to delete File based on given ID
     * @param id ID of file to delete
     * @return true if file deleted, false if file not found
     */
    boolean deleteFile(String id);

    /**
     * Checks if File already exists on filesystem or in database
     * @param id ID of file to check
     * @return true if already exists, false if not
     */
    boolean checkForExistence(String id);

    /**
     * Finds UploadedFile by ID
     * @param id ID to look for
     * @return UploadedFile Entity, or null if not found
     */
    UploadedFile findById(String id);
}
