package de.haw.aim.vendor.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VendorInfoRepository extends MongoRepository<VendorInfo, String>
{
}
