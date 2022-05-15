package com.finance.web.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class MemberTest {

    @Test
    void create() {
        Member member = Member.builder()
                .email("test")
                .password("1234")
                .username("testname")
                .subscribe(true)
                .build();

        assertThat(member.getEmail()).isEqualTo("test");
        assertThat(member.getPassword()).isEqualTo("1234");
        assertThat(member.getUsername()).isEqualTo("testname");
        assertThat(member.getSubscribe()).isEqualTo(true);
    }

}