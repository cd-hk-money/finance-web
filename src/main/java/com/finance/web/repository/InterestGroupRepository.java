package com.finance.web.repository;

import com.finance.web.entity.InterestGroup;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InterestGroupRepository extends MongoRepository<InterestGroup, ObjectId> {


}
