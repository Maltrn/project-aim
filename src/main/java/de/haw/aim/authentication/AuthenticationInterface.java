package de.haw.aim.authentication;

import de.haw.aim.authentication.persistence.User;

public interface AuthenticationInterface
{
    /**
     * Returns a User based on a given token
     * @param token token to look for
     * @return returns a User entity, or null if token not found
     */
    User findByToken(String token);

    User login(String username, String pw);

    User create(String username, String pw);

    Boolean verifyToken(String token);
}