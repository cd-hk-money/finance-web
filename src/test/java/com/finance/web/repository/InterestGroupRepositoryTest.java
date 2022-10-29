package com.finance.web.repository;

import com.finance.web.domain.InterestGroup;
import com.finance.web.domain.Member;
import com.finance.web.dto.InterestGroupDto;
import com.finance.web.dto.InterestGroupUpdateDto;
import com.finance.web.vo.StockItem;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.mongodb.assertions.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class InterestGroupRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    InterestGroupRepository interestGroupRepository;

    @Autowired
    MongoTemplate mongoTemplate;


    @Test
    @DisplayName("관심종목 그룹 추가")
    void createInterestGroup() throws Exception {
        // given
        Member member = Member.builder()
                .email("test@admin.com")
                .password("1234")
                .username("홍길동")
                .notifications(new HashSet<>())
                .subscription(false)
                .build();

        ObjectId memberId = memberRepository.save(member).get_id();

        //when
        InterestGroup interestGroup = InterestGroup.builder()
                .name("전자")
                .sequence(1)
                .interests(new ArrayList<>())
                .memberId(memberId)
                .build();

        InterestGroup save = interestGroupRepository.save(interestGroup);

        //then
        assertThat(save.get_id()).isEqualTo(interestGroup.get_id());
        assertThat(save.getMemberId()).isEqualTo(interestGroup.getMemberId());
        assertThat(save.getName()).isEqualTo(interestGroup.getName());
        assertThat(save.getSequence()).isEqualTo(interestGroup.getSequence());
    }

    @Test
    @DisplayName("관심종목 그룹 조회")
    void readInterestGroup() throws Exception {
        // given
        Member member = Member.builder()
                .email("test@admin.com")
                .password("1234")
                .username("홍길동")
                .notifications(new HashSet<>())
                .subscription(false)
                .build();

        ObjectId memberId = memberRepository.save(member).get_id();

        InterestGroup 전자 = InterestGroup.builder()
                .name("전자")
                .sequence(1)
                .interests(new ArrayList<>())
                .memberId(memberId)
                .build();

        InterestGroup 중공업 = InterestGroup.builder()
                .name("중공업")
                .sequence(2)
                .interests(new ArrayList<>())
                .memberId(memberId)
                .build();

        InterestGroup it = InterestGroup.builder()
                .name("it")
                .sequence(3)
                .interests(new ArrayList<>())
                .memberId(memberId)
                .build();

        interestGroupRepository.saveAll(Arrays.asList(전자, 중공업, it));

        //then
        LinkedHashSet<InterestGroupDto> interestGroupsByMemberId = interestGroupRepository.findInterestGroupsByMemberId(memberId);
        assertThat(interestGroupsByMemberId.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("관심종목 그룹 수정")
    void updateInterestGroup() throws Exception {
        // given
        Member member = Member.builder()
                .email("test@admin.com")
                .password("1234")
                .username("홍길동")
                .notifications(new HashSet<>())
                .subscription(false)
                .build();

        ObjectId memberId = memberRepository.save(member).get_id();

        InterestGroup 전자 = InterestGroup.builder()
                .name("전자")
                .sequence(1)
                .interests(new ArrayList<>())
                .memberId(memberId)
                .build();

        InterestGroup 중공업 = InterestGroup.builder()
                .name("중공업")
                .sequence(2)
                .interests(new ArrayList<>())
                .memberId(memberId)
                .build();

        InterestGroup it = InterestGroup.builder()
                .name("it")
                .sequence(3)
                .interests(new ArrayList<>())
                .memberId(memberId)
                .build();

        interestGroupRepository.saveAll(Arrays.asList(전자, 중공업, it));

        InterestGroupUpdateDto updateDto = InterestGroupUpdateDto.builder()
                .name("not even it anymore")
                .sequence(5)
                .build();

        boolean b = interestGroupRepository.updateInterestGroup(it.get_id(), updateDto);
        InterestGroup interestGroup = interestGroupRepository.findById(it.get_id()).orElseThrow();

        //then
        assertTrue(b);
        assertThat(interestGroup.getName()).isEqualTo("not even it anymore");
        assertThat(interestGroup.getSequence()).isEqualTo(5);
    }

    @Test
    @DisplayName("관심종목 그룹 수정2")
    void updateInterestGroup2() throws Exception {
        // given
        Member member = Member.builder()
                .email("test@admin.com")
                .password("1234")
                .username("홍길동")
                .notifications(new HashSet<>())
                .subscription(false)
                .build();

        ObjectId memberId = memberRepository.save(member).get_id();

        List<StockItem> stockItems = Arrays.asList(new StockItem("035720", "카카오"), new StockItem("323410", "카카오뱅크")
                , new StockItem("035420", "네이버"), new StockItem("017670", "SK텔레콤"));

        InterestGroup it = InterestGroup.builder()
                .name("it")
                .sequence(1)
                .interests(stockItems)
                .memberId(memberId)
                .build();

        InterestGroup interestGroup = interestGroupRepository.save(it);

        //when
        List<StockItem> updateItems = Arrays.asList(new StockItem("005930", "삼성전자"), new StockItem("000660", "SK하이닉스"));

        InterestGroupUpdateDto updateDto = InterestGroupUpdateDto.builder()
                .name("전자")
                .stockItems(updateItems)
                .sequence(1)
                .build();

        interestGroupRepository.updateInterestGroup(interestGroup.get_id(), updateDto);

        //then
        InterestGroup updatedGroup = interestGroupRepository.findById(interestGroup.get_id()).orElseThrow();
        assertThat(updatedGroup.getName()).isEqualTo("전자");
        assertThat(updatedGroup.getMemberId()).isEqualTo(interestGroup.getMemberId());
        assertThat(updatedGroup.getSequence()).isEqualTo(interestGroup.getSequence());
        assertThat(updatedGroup.getInterests().size()).isEqualTo(4);
        updateItems.stream().map(StockItem::toString).forEach(System.out::println);

    }

    @Test
    @DisplayName("관심종목 그룹 수정3")
    void updateInterestGroup3() throws Exception {
        // given
        Member member = Member.builder()
                .email("test@admin.com")
                .password("1234")
                .username("홍길동")
                .notifications(new HashSet<>())
                .subscription(false)
                .build();

        ObjectId memberId = memberRepository.save(member).get_id();

        List<StockItem> stockItems = Arrays.asList(new StockItem("035720", "카카오"), new StockItem("323410", "카카오뱅크")
                , new StockItem("035420", "네이버"), new StockItem("017670", "SK텔레콤"));

        InterestGroup it = InterestGroup.builder()
                .name("it")
                .sequence(1)
                .interests(stockItems)
                .memberId(memberId)
                .build();

        InterestGroup interestGroup = interestGroupRepository.save(it);

        //when
        List<StockItem> updateItems = Arrays.asList(new StockItem("005930", "삼성전자"), new StockItem("000660", "SK하이닉스"));

        interestGroupRepository.updateInterests(interestGroup.get_id(), updateItems);

        //then
        InterestGroup updatedGroup = interestGroupRepository.findById(interestGroup.get_id()).orElseThrow();
        assertThat(updatedGroup.getName()).isEqualTo("it");
        assertThat(updatedGroup.getMemberId()).isEqualTo(interestGroup.getMemberId());
        assertThat(updatedGroup.getSequence()).isEqualTo(interestGroup.getSequence());
        assertThat(updatedGroup.getInterests().size()).isEqualTo(2);
        updateItems.stream().map(StockItem::toString).forEach(System.out::println);

    }

    @Test
    @DisplayName("관심종목 그룹 삭제")
    void deleteInterestGroup() throws Exception {
        // given
        Member member = Member.builder()
                .email("test@admin.com")
                .password("1234")
                .username("홍길동")
                .notifications(new HashSet<>())
                .subscription(false)
                .build();

        ObjectId memberId = memberRepository.save(member).get_id();

        InterestGroup 전자 = InterestGroup.builder()
                .name("전자")
                .sequence(1)
                .interests(new ArrayList<>())
                .memberId(memberId)
                .build();

        InterestGroup 중공업 = InterestGroup.builder()
                .name("중공업")
                .sequence(2)
                .interests(new ArrayList<>())
                .memberId(memberId)
                .build();

        InterestGroup it = InterestGroup.builder()
                .name("it")
                .sequence(3)
                .interests(new ArrayList<>())
                .memberId(memberId)
                .build();

        interestGroupRepository.saveAll(Arrays.asList(전자, 중공업, it));

        LinkedHashSet<InterestGroupDto> interestGroupsByMemberId = interestGroupRepository.findInterestGroupsByMemberId(memberId);
        assertThat(interestGroupsByMemberId.size()).isEqualTo(3);

        //then
        interestGroupRepository.delete(it);
        assertThat(interestGroupRepository.findInterestGroupsByMemberId(memberId).size()).isEqualTo(2);

    }


}