package de.haw.aim.authentication.persistence;

/**
 * Created by Rene on 31.10.2016.
 */
public class User {
    String id;
    String username;
    String pw;
    String currentToken;

    public String getUsername() {
        return username;
    }

    public String getCurrentToken() {
        return currentToken;
    }
}
