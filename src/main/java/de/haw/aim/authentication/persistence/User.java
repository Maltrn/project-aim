package de.haw.aim.authentication.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Rene on 31.10.2016.
 */
@Document
public class User {
    @Id
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
