package com.finance.web.repository;

import com.finance.web.domain.Member;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;

@Transactional
public interface MemberRepository extends MongoRepository<Member, ObjectId>, CustomMemberRepository {

    Optional<Member> findMemberByUsername(String username);

    boolean existsMemberByEmailEquals(String email);

    boolean existsMemberByUsernameEquals(String username);

    HashSet<Member> findMemberByNotificationsContaining(@Param("stockCode") String stockCode);

}