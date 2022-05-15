package com.finance.web.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class InterestGroupTest {

    Member member;

    @BeforeEach
    void init() {
        member = Member.builder()
                .email("test")
                .password("1234")
                .username("testname")
                .subscribe(true)
                .build();
    }

    @Test
    void create() {
        InterestGroup interestGroup = InterestGroup.builder()
                .member(member)
                .sequence(1)
                .name("부동산")
                .build();

        assertThat(interestGroup.getMember().getId()).isEqualTo(member.getId());
        assertThat(interestGroup.getName()).isEqualTo("부동산");
        assertThat(interestGroup.getSequence()).isEqualTo(1);
    }
}