package com.finance.web.repository;

import com.finance.web.entity.InterestList;
import com.finance.web.entity.Member;
import com.netflix.discovery.converters.Auto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class InterestListRepositoryTest {

    @Autowired
    InterestListRepository interestListRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    void 생성() {
        Member member = Member.builder()
                .email("email@test.com")
                .password("1234")
                .username("john doe").build();

        InterestList IL = InterestList.builder()
                .name("반도체")
                .member(member)
                .sequence(1)
                .build();

        Member savedMember = memberRepository.save(member);
        InterestList savedIL = interestListRepository.save(IL);

        assertThat(savedIL.getMember()).isEqualTo(savedMember);
        assertThat(savedIL.getMember()).isEqualTo(member);

    }
}