package de.haw.aim.uploadcenter.persistence;

public interface UploadCenterInterface {
    public File uploadFile(File f);

    public File replaceFile(String id, File f);

    public boolean deleteFile(String id);

    public boolean checkForExistence(String id);
}
