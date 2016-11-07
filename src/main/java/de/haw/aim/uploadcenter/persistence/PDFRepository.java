package de.haw.aim.uploadcenter.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PDFRepository extends MongoRepository<PDF, String>
{

}
