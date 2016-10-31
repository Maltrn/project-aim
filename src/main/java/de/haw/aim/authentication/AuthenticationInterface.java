package de.haw.aim.authentication;

import de.haw.aim.authentication.persistence.User;

/**
 * Created by Rene on 31.10.2016.
 */
public interface AuthenticationInterface {
    User login(String username, String pw);
    Boolean verifyToken(String token);
}
