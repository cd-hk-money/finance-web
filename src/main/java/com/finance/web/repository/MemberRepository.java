package com.finance.web.repository;

import com.finance.web.domain.Member;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.HashSet;

public interface MemberRepository extends MongoRepository<Member, ObjectId>, CustomMemberRepository {

    boolean existsMemberByEmailEquals(String email);

    boolean existsMemberByUsernameEquals(String username);

    HashSet<Member> findMemberByNotificationsContaining(@Param("stockCode") String stockCode);

}