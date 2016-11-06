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
        // try to get user from DB
        User retVal = userRepository.findByUsername(username);

        // if user found check credentials
        if(retVal != null)
        {
            // invalid password leads to null return
            if(!retVal.checkPW(pw))
                return null;

            // otherwise login call is valid and a token needs to be generated for the user
            Random random = new SecureRandom();
            String token = new BigInteger(130, random).toString(32);

            // as long we can find the currently generated token in the database, we have to generate
            // a new one
            while(userRepository.findByCurrentToken(token) != null)
            {
                token = new BigInteger(130, random).toString(32);
            }

            // set the current token in the current user entity and save it
            // save returns the "up to date" persisted user entity
            retVal.setCurrentToken(token);
            return userRepository.save(retVal);
        }

        // if no user is found with specified username return null
        return null;
    }

    @Override
    public Boolean verifyToken(String token)
    {
        // find a user in db which has the token to verify, if user equals null return false
        User retVal = userRepository.findByCurrentToken(token);
        return retVal instanceof User;
    }
}
