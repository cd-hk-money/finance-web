package com.finance.web.repository;

import com.finance.web.domain.Member;
import com.finance.web.vo.StockItem;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.HashSet;

public interface MemberRepository extends MongoRepository<Member, ObjectId>, CustomMemberRepository {

    HashSet<Member> findMemberByNotificationsContaining(@Param("stockCode") String stockCode);

}