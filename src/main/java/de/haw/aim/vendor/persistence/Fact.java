package de.haw.aim.vendor.persistence;

/**
 * Created by Rene on 31.10.2016.
 */
public class Fact {
    private String key;
    private String value;

    public Fact(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
