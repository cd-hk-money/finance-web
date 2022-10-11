package com.finance.web.service;

import com.finance.web.vo.Message;
import com.finance.web.vo.StockItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.mongodb.assertions.Assertions.assertTrue;

@SpringBootTest
class MessageServiceTest {

    @Autowired
    MessageService messageService;

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    @DisplayName("메시지 보내기")
    void sendMessageToMembers() throws Exception {
        //given
        StockItem stockItem = new StockItem("005930", "삼성전자");
        LocalDateTime now = LocalDateTime.now();
        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
        Message message = Message.builder()
                .createdDate(formatedNow)
                .stockItem(new StockItem("005930", "삼성전자"))
                .title("삼성전자 다시 7만원 급등!!!")
                .content("영차 영차!!!")
                .isChecked(Boolean.FALSE)
                .build();

        //when
        boolean b = messageService.sendMessageToMembers(stockItem, message);

        //then
        assertTrue(b);
    }
}