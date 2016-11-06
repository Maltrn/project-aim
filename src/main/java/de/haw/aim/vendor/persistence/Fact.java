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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fact fact = (Fact) o;

        if (!key.equals(fact.key)) return false;
        return value.equals(fact.value);

    }

    @Override
    public int hashCode() {
        int result = key.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }
}
