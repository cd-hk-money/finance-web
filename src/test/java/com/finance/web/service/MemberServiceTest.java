package com.finance.web.service;

import com.finance.web.dto.MemberSaveRequestDto;
import com.finance.web.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberServiceImpl memberService;

    @Test
    void join() throws Exception {
        // when
        MemberSaveRequestDto memberSaveRequestDto = MemberSaveRequestDto.builder()
                .email("nanana@test.org")
                .username("happy")
                .password("1234!")
                .subscription(true)
                .build();

        // then
        Long memberId = memberService.join(memberSaveRequestDto);
        Optional<Member> findOne = memberService.findOne(memberId);

        assertThat(findOne.get().getUsername()).isEqualTo(memberSaveRequestDto.getUsername());
        assertThat(findOne.get().getEmail()).isEqualTo(memberSaveRequestDto.getEmail());
        assertThat(findOne.get().getPassword()).isEqualTo(memberSaveRequestDto.getPassword());
        assertThat(findOne.get().getSubscription()).isEqualTo(memberSaveRequestDto.getSubscription());
    }

    @Test()
    void 중복검사() {
        Assertions.assertThrows(IllegalStateException.class, () -> {


        });
    }
}