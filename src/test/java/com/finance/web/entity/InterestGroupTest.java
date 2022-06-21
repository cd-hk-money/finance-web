package com.finance.web.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InterestGroupTest {

    @Autowired
    MongoTemplate mongoTemplate;

    Member member;

    @BeforeEach
    void init() {
        Query query = new Query(new Criteria().andOperator(
                Criteria.where("email").is("test@1234.com")
        ));

        member = mongoTemplate.findOne(query, Member.class);
    }

    @Test
    void test() throws Exception {
        //given

        List<Interest> interestList = new ArrayList<>();

        Interest one = new Interest( "005930", "삼성전자");
        Interest two = new Interest("066570", "LG전자");
        Interest three = new Interest("079370", "제우스");
        Interest four = new Interest("005930", "SK하이닉스");

        for (Interest interest : Arrays.asList(one, two, three, four)) {
            interestList.add(interest);
        }

        InterestGroup interestGroup = InterestGroup.builder()
                .name("전자")
                .memberId(member.getId())
                .interests(interestList)
                .build();
        //when

        mongoTemplate.save(interestGroup);
        //then

    }

}