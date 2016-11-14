package de.haw.aim.authentication.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>
{
    User findByUsername(String username);

    User findByCurrentToken(String currentToken);
}
