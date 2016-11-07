package de.haw.aim.uploadcenter.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PictureRepository extends MongoRepository<Picture, String>
{

}