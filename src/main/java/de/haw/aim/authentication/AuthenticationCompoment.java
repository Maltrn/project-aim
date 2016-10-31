package de.haw.aim.authentication;

import de.haw.aim.authentication.persistence.User;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationCompoment implements AuthenticationInterface{

    @Override
    public User login(String username, String pw) {
        return null;
    }

    @Override
    public Boolean verifyToken(String token) {
        return null;
    }
}
