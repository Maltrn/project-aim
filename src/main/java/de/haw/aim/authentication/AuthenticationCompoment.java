package de.haw.aim.authentication;

import de.haw.aim.authentication.persistence.User;
import de.haw.aim.authentication.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

@Component
public class AuthenticationCompoment implements AuthenticationInterface
{

    @Autowired
    private
    UserRepository userRepository;

    @Override
    public User findByToken(String token)
    {
        return userRepository.findByCurrentToken(token);
    }

    @Override
    public User login(String username, String pw)
    {
        // try to get user.json from DB
        //create(username, pw);
        User retVal = userRepository.findByUsername(username);

        // if user.json found check credentials
        if (retVal != null)
        {
            // invalid password leads to null return
            if (!retVal.checkPW(pw))
                return null;

            // otherwise login call is valid and a token needs to be generated for the user.json
            Random random = new SecureRandom();
            String token = new BigInteger(130, random).toString(32);

            // as long we can find the currently generated token in the database, we have to generate
            // a new one
            while (userRepository.findByCurrentToken(token) != null)
            {
                token = new BigInteger(130, random).toString(32);
            }

            // set the current token in the current user.json entity and save it
            // save returns the "up to date" persisted user.json entity
            retVal.setCurrentToken(token);
            return userRepository.save(retVal);
        }

        // if no user.json is found with specified username return null
        return null;
    }

    @Override
    public User create(String username, String pw) {
        // try to get user.json from DB
        User retVal = userRepository.findByUsername(username);
        if (retVal == null)
        { // User does not exist so we can try to create it
            User user = new User(username, pw);
            return userRepository.save(user);
        } else
        { // User already exists so we cannot create a user.json with the same username
            return null;
        }
    }

    @Override
    public Boolean verifyToken(String token)
    {
        // find a user.json in db which has the token from verify, if user.json equals null return false
        User retVal = userRepository.findByCurrentToken(token);
        return retVal instanceof User;
    }
}
