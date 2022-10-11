package com.finance.web.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest
class MessageServiceImplTest {

    @Autowired
    MessageService messageService;

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    @DisplayName("메시지 보내기")
    void sendMessageToMembers() throws Exception {
        //given

        //when

        //then

    }
}