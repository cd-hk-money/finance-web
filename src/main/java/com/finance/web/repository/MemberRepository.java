package com.finance.web.repository;

import com.finance.web.entity.Member;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberRepository extends MongoRepository<Member, ObjectId> {

}
