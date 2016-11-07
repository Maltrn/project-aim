package de.haw.aim.authentication.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Rene on 06.11.2016.
 */
public interface UserRepository extends MongoRepository<User, String>
{
    User findByUsername(String username);

    User findByCurrentToken(String currentToken);
}
