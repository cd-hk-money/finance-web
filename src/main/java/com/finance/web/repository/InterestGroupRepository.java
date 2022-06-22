package com.finance.web.repository;

import com.finance.web.domain.InterestGroup;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InterestGroupRepository extends MongoRepository<InterestGroup, ObjectId>, CustomInterestGroupRepository {

}
