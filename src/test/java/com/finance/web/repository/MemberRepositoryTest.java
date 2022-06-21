package com.finance.web.repository;

import com.finance.web.entity.Member;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    MemberRepository memberRepository;

    Member member;


    @BeforeEach
    void init() {
        Query query = new Query(new Criteria().andOperator(
                Criteria.where("email").is("test@1234.com")));

        member = mongoTemplate.findOne(query, Member.class);
    }


    @Test
    void pushItemToNotifications() throws Exception {
        //given
        String item = "123456";
        ObjectId objectId = new ObjectId("62b1ad6c4511dc1e6b9afddf");

        //when
        memberRepository.pushItemToNotifications(objectId, item);

        //then
    }

    @Test
    void findByItemInNotification() {
        String item = "123456";

        List<Member> byStockCodeInNotifications = memberRepository.findByStockCodeInNotifications("123456");
        byStockCodeInNotifications.forEach(byStockCodeInNotification -> {
            System.out.println("byStockCodeInNotification.getEmail() = " + byStockCodeInNotification.getEmail());
            System.out.println("byStockCodeInNotification.getUsername() = " + byStockCodeInNotification.getUsername());
        });
    }

    @Test
    void deleteItemToNotifications() throws Exception {
        //given
        String item = "123456";
        ObjectId objectId = new ObjectId("62b1ad6c4511dc1e6b9afddf");

        //when
        memberRepository.deleteItemFromNotifications(objectId, item);

        //then
    }
}