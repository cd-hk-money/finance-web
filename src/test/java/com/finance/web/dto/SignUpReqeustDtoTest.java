package com.finance.web.dto;

import com.finance.web.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SignUpReqeustDtoTest {

    @Test
    @DisplayName("DTO 생성")
    void create() throws Exception {
        SignUpRequestDto signUpRequestDto = SignUpRequestDto.builder()
                .email("wkdwoo@kakako.com")
                .password("1234")
                .username("장길동")
                .nickname("장꺽정")
                .build();

        assertThat(signUpRequestDto.getEmail()).isEqualTo("wkdwoo@kakako.com");
        assertThat(signUpRequestDto.getPassword()).isEqualTo("1234");
        assertThat(signUpRequestDto.getUsername()).isEqualTo("장길동");
        assertThat(signUpRequestDto.getNickname()).isEqualTo("장꺽정");
    }

    @Test
    @DisplayName("Dto To Document")
    void toDocument() throws Exception {
        //given
        SignUpRequestDto signUpRequestDto = SignUpRequestDto.builder()
                .email("wkdwoo@kakako.com")
                .password("1234")
                .username("장길동")
                .nickname("장꺽정")
                .build();

        //when
        Member member = signUpRequestDto.toDocument();

        //then
        assertThat(member.getEmail()).isEqualTo(signUpRequestDto.getEmail());
        assertThat(member.getPassword()).isEqualTo(signUpRequestDto.getPassword());
        assertThat(member.getUsername()).isEqualTo(signUpRequestDto.getUsername());
        assertThat(member.getSubscription()).isEqualTo(false);
    }
}