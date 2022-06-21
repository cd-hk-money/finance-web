package com.finance.web.repository;

import com.finance.web.entity.Member;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.HashSet;
import java.util.List;

public interface MemberRepository extends MongoRepository<Member, ObjectId>, CustomMemberRepository {

    public HashSet<Member> findMemberByNotificationsContaining(@Param("stockCode") String stockCode);

}