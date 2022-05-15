package com.finance.web.repository;

import com.finance.web.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    void testMember() {
        Member member = Member.builder()
                .email("email@test.com")
                .password("1234")
                .username("john doe")
                .subscription(true)
                .build();

        Member savedMember = memberRepository.save(member);
        Optional<Member> findMember = memberRepository.findOneByUsername("john doe");

        assertThat(findMember.get().getId()).isEqualTo(savedMember.getId());
        assertThat(findMember.get().getPassword()).isEqualTo(savedMember.getPassword());
        assertThat(findMember.get().getUsername()).isEqualTo(savedMember.getUsername());
    }

    @Test
    void findListByUsername() {
        Member m1 = Member.builder()
                .email("test")
                .password("1234")
                .username("test1").build();

        Member m2 = Member.builder()
                .email("test2")
                .password("1234")
                .username("test2").build();

        Member m3 = Member.builder()
                .email("test3")
                .password("1234")
                .username("test3").build();

        Member m4 = Member.builder()
                .email("test4")
                .password("1234")
                .username("test4").build();

        List<Member> members = Arrays.asList(m1, m2, m3, m4);

        List<Member> savedMembers = memberRepository.saveAll(members);
        List<Member> findMembers = memberRepository.findByUsernameContaining("test");

        assertThat(findMembers.size()).isEqualTo(savedMembers.size());

    }


}