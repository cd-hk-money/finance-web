package com.finance.web.repository;

import com.finance.web.entity.InterestGroup;
import com.finance.web.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class InterestGroupRepositoryTest {

    @Autowired
    InterestGroupRepository interestGroupRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    Member member;


    @BeforeEach
    void init() {
        Member member = Member.builder()
                .email("email@test.com")
                .password("1234")
                .username("john doe").build();

        memberRepository.save(member);
    }

    @Test
    void create() {
        InterestGroup IL = InterestGroup.builder()
                .name("반도체")
                .member(member)
                .sequence(1)
                .build();

        InterestGroup savedIL = interestGroupRepository.save(IL);

        assertThat(savedIL.getId()).isEqualTo(IL.getId());
    }

    @Test
    void read() throws Exception {
        //given
        InterestGroup IL = InterestGroup.builder()
                .name("반도체")
                .member(member)
                .sequence(1)
                .build();

        InterestGroup savedIL = interestGroupRepository.save(IL);

        //when
        List<InterestGroup> findILs = interestGroupRepository.findAllByMember(member);

        //then
        assertThat(findILs.get(0)).isEqualTo(savedIL);

        for (InterestGroup findIL : findILs) {
            assertThat(findIL.getMember().getId()).isEqualTo(member.getId());
        }
    }

    @Test
    void update() {
        //given
        InterestGroup IL = com.finance.web.entity.InterestGroup.builder()
                .name("반도체")
                .member(member)
                .sequence(1)
                .build();

        interestGroupRepository.save(IL);

        em.flush();
        em.clear();

        //when
        interestGroupRepository.updateName(IL.getId(), "부동산"); // 쿼리

        em.flush();
        em.clear();

        Optional<InterestGroup> findIL = interestGroupRepository.findById(IL.getId());
        assertThat(findIL).isPresent();

        //then
        assertThat(findIL.get().getName()).isEqualTo("부동산");

        System.out.println("findIL = " + findIL.get().getName());
        System.out.println("findIL.sequence = " + findIL.get().getSequence());
    }

    @Test
    void delete() throws Exception {
        //given

        InterestGroup IL = InterestGroup.builder()
                .name("반도체")
                .member(member)
                .sequence(1)
                .build();

        InterestGroup savedIL = interestGroupRepository.save(IL);

        Long ilId = IL.getId();

        //when
        Optional<InterestGroup> findIL = interestGroupRepository.findById(ilId);
        assertThat(findIL).isPresent();

        findIL.ifPresent(selectedIL -> {
            interestGroupRepository.delete(savedIL);
        });

        //then
        assertThat(interestGroupRepository.findById(ilId)).isEmpty();
        System.out.println("savedIL = " + savedIL);
    }
}