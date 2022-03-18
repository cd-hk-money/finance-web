package com.finance.web.service;

import com.finance.web.entity.Member;
import org.junit.jupiter.api.Assertions;
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
    void testMember() {
        Member member = Member.builder()
                .email("test@email")
                .username("john doe")
                .password("1234")
                .build();
        Long memberId = memberService.join(member);
        Optional<Member> findOne = memberService.findOne(memberId);

        assertThat(findOne.get().getId()).isEqualTo(member.getId());
        assertThat(findOne.get().getEmail()).isEqualTo(member.getEmail());
        assertThat(findOne.get().getPassword()).isEqualTo(member.getPassword());
    }

    @Test()
    void 중복검사() {
        Assertions.assertThrows(IllegalStateException.class, () -> {

            Member m1 = Member.builder()
                    .email("test@email")
                    .username("john doe")
                    .password("1234")
                    .build();
            memberService.join(m1);
            Member m2 = Member.builder()
                    .email("test@email")
                    .username("john doe")
                    .password("1234")
                    .build();
            memberService.join(m2);
        });
    }
}