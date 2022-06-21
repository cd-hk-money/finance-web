package com.finance.web.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InterestTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    void basicTest() {
    }

}