package com.finance.web.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(value = true)
class MemberTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    void basic_test() throws Exception {
        //given
        Member member = Member.builder()
                .email("test@1234.com")
                .password("1234")
                .username("홍길동")
                .subscription(false)
                .build();
        //when

        mongoTemplate.save(member);

        //then
    }

}