package de.haw.aim.vendor.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface InfoRepository extends MongoRepository<Info, String>
{
}