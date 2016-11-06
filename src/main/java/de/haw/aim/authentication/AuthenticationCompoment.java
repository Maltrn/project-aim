package de.haw.aim.authentication;

import de.haw.aim.authentication.persistence.User;
import de.haw.aim.authentication.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

@Component
public class AuthenticationCompoment implements AuthenticationInterface{

    @Autowired
    UserRepository userRepository;

    @Override
    public User login(String username, String pw) {
        User retVal = userRepository.findByUsername(username);

        if(retVal != null)
        {
            if(!retVal.checkPW(pw))
                return null;

            Random random = new SecureRandom();
            String token = new BigInteger(130, random).toString(32);

            while(userRepository.findByCurrentToken(token) != null)
            {
                token = new BigInteger(130, random).toString(32);
            }

            retVal.setCurrentToken(token);
            return userRepository.save(retVal);
        }

        return null;
    }

    @Override
    public Boolean verifyToken(String token)
    {
        User retVal = userRepository.findByCurrentToken(token);
        return retVal instanceof User;
    }
}
