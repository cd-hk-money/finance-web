package com.finance.web.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@SpringBootTest
@Transactional
class MemberTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    void basic_test() throws Exception {
        //given
        Member member = Member.builder()
                .email("test@admin.com")
                .password("1234")
                .username("홍길동")
                .notifications(new HashSet<>())
                .subscription(false)
                .build();
        //when

        mongoTemplate.save(member);

        //then
    }

    @Test
    void notification() {
        Query query = new Query(new Criteria().andOperator(
                Criteria.where("email").is("test@1234.com")
        ));

        Member member = mongoTemplate.findOne(query, Member.class);

    }

}