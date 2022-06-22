package com.finance.web.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest
class InterestTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    void basicTest() {
    }

}