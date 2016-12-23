package de.haw.aim.authentication.facade;

import de.haw.aim.authentication.persistence.User;

public interface AuthenticationInterface
{
    /**
     * Returns a User based on a given token. Just for internal use
     * @param token token to look for
     * @return returns a User entity, or null if token not found
     */
    User findByToken(String token);

    /**
     * Tries to login with username and pw
     * @param username
     * @param pw
     * @return returns a User entity, or null if username or pw wrong
     */
    User login(String username, String pw);

    /**
     * Creates a user. Creation only possible if user does not exist
     * @param username username for the user
     * @param pw password for the user, saved plain to db - make sure this is hashed before
     * @return Returns user entity, null if user already exist
     */
    User create(String username, String pw);

    /**
     * Verifies user token to check if user is logged in and has access. Since tokens are unique we can only check
     * token
     * @param token Token to verify
     * @return true if token found, false if no token found
     */
    Boolean verifyToken(String token);
}