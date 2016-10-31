package de.haw.aim.uploadcenter.persistence;

public class Picture implements File {
    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getLocation() {
        return null;
    }

    @Override
    public String getId() {
        return null;
    }
}
