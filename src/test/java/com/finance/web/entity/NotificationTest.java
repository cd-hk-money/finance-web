package com.finance.web.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NotificationTest {

    @Test
    void create() {
        Notification noti = Notification.builder()
                .stockCode("035720")
                .title("카카오 확정실적 발표")
                .sentence("2021년 매출액 xxx조").build();

        assertThat(noti.getStockCode()).isEqualTo("035720");
        assertThat(noti.getTitle()).isEqualTo("카카오 확정실적 발표");
        assertThat(noti.getSentence()).isEqualTo("2021년 매출액 xxx조");
    }
}