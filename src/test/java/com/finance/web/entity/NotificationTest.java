package com.finance.web.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NotificationTest {

    @Test
    void create() {
        Notification noti = new Notification("035720",
                "카카오 확정실적 발표", "2021년 매출액 xxx조");

        assertThat(noti.getStockCode()).isEqualTo("035720");
        assertThat(noti.getTitle()).isEqualTo("카카오 확정실적 발표");
        assertThat(noti.getSentence()).isEqualTo("2021년 매출액 xxx조");
    }
}