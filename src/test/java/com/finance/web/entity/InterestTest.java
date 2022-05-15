package com.finance.web.entity;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class InterestTest {

    @Test
    void create() {
        Interest interest = Interest.builder()
                .stockCode("005930")
                .interestGroupId(1L)
                .sequence(1)
                .notification(true)
                .build();

        assertThat(interest.getStockCode()).isEqualTo("005930");
        assertThat(interest.getInterestGroupId()).isEqualTo(1L);
        assertThat(interest.getSequence()).isEqualTo(1);
        assertThat(interest.getNotification()).isEqualTo(true);
    }

}