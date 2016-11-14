package de.haw.aim.authentication;

import de.haw.aim.authentication.persistence.User;

/**
 * Created by Rene on 31.10.2016.
 */
public interface AuthenticationInterface
{
    /**
     *
     * @param token
     * @return
     */
    User findByToken(String token);

    User login(String username, String pw);

    User create(String username, String pw);

    Boolean verifyToken(String token);
}
