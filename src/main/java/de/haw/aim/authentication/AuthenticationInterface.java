package de.haw.aim.authentication;

import de.haw.aim.authentication.persistence.User;

public interface AuthenticationInterface
{
    /**
     * @param token
     * @return
     */
    User findByToken(String token);

    User login(String username, String pw);

    Boolean verifyToken(String token);
}