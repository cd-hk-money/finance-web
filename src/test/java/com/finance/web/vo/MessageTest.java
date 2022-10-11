package com.finance.web.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.mongodb.assertions.Assertions.assertFalse;
import static com.mongodb.assertions.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

class MessageTest {

    @Test
    @DisplayName("메시지 유닛테스트")
    void create() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));

        Message message = Message.builder()
                .createdDate(formatedNow)
                .stockItem(new StockItem("005930", "삼성전자"))
                .title("삼성전자 10만원 달성!")
                .content("드디어 십만전자로 돌아왔어용")
                .isChecked(false)
                .build();

        assertThat(message.getTitle()).isEqualTo("삼성전자 10만원 달성!");
        assertThat(message.getContent()).isEqualTo("드디어 십만전자로 돌아왔어용");
        assertThat(message.getStockItem().getStockCode()).isEqualTo("005930");
        assertThat(message.getStockItem().getStockName()).isEqualTo("삼성전자");
        assertTrue(message.getCreatedDate().contains("2022"));
    }

    @Test
    @DisplayName("메시지 확인여부 유닛테스트")
    void change() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));

        Message message = Message.builder()
                .createdDate(formatedNow)
                .stockItem(new StockItem("005930", "삼성전자"))
                .title("삼성전자 10만원 달성!")
                .content("드디어 십만전자로 돌아왔어용")
                .isChecked(false)
                .build();

        assertFalse(message.getIsChecked());
        assertTrue(message.checkMessage());
        assertTrue(message.getIsChecked());
    }

}