package com.finance.web.repository;

import com.finance.web.entity.Interest;
import com.finance.web.entity.InterestList;
import com.finance.web.entity.Member;
import com.netflix.discovery.converters.Auto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

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
    void create() {
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

    @Test
    void read() throws Exception {
        //given
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

        //when
        List<InterestList> findILs = interestListRepository.findAllByMember(member);

        //then
        assertThat(findILs.get(0)).isEqualTo(savedIL);
        assertThat(findILs.get(0).getMember()).isEqualTo(savedMember);
    }

    @Test
    void update() throws Exception {
        //given
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

        Long ilId = IL.getId();

        //when
        IL.changeSequence(2);
        IL.updateName("부동산"); // hibernate 변경감지
        Optional<InterestList> findIL = interestListRepository.findById(ilId);
        assertThat(findIL).isPresent();

        //then
        assertThat(findIL.get().getSequence()).isEqualTo(2);
        assertThat(findIL.get().getName()).isEqualTo("부동산");

        System.out.println("findIL = " + findIL.get().getName());
        System.out.println("findIL.sequence = " + findIL.get().getSequence());
    }

    @Test
    void delete() throws Exception {
        //given
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

        Long ilId = IL.getId();

        //when
        Optional<InterestList> findIL = interestListRepository.findById(ilId);
        assertThat(findIL).isPresent();

        findIL.ifPresent(selectedIL -> {
            interestListRepository.delete(savedIL);
        });

        //then
        assertThat(interestListRepository.findById(ilId)).isEmpty();
        System.out.println("savedIL = " + savedIL);
    }
}