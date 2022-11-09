package com.finance.web.service;

import com.finance.web.domain.InterestGroup;
import com.finance.web.domain.Member;
import com.finance.web.dto.InterestGroupDto;
import com.finance.web.dto.InterestGroupUpdateDto;
import com.finance.web.repository.MemberRepository;
import com.finance.web.vo.StockItem;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class InterestGroupServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    InterestGroupService interestGroupService;

    @Autowired
    MongoTemplate mongoTemplate;

    Member member;
    ObjectId memberId;

    @BeforeEach
    void init() {
        member = Member.builder()
                .email("test@admin.com")
                .password("1234")
                .username("홍길동")
                .notifications(new HashSet<>())
                .subscription(false)
                .build();

        memberId = memberRepository.save(member).get_id();
    }

    @Test
    @DisplayName("관심종목 그룹 추가")
    void createInterestGroup() throws Exception {
        //given
        InterestGroupDto groupDto = InterestGroupDto.builder()
                .name("전자")
                .sequence(1)
                .interests(new ArrayList<>())
                .memberId(memberId.toString())
                .build();

        //when
        InterestGroupDto interestGroup = interestGroupService.addInterestGroup(groupDto);

        //then
        assertThat(interestGroup.getName()).isEqualTo(groupDto.getName());
        assertThat(interestGroup.getSequence()).isEqualTo(groupDto.getSequence());
        assertThat(interestGroup.getMemberId()).isEqualTo(groupDto.getMemberId());
        System.out.println("dto = " + groupDto);
    }

    @Test
    @DisplayName("관심종목 그룹 조회")
    void readInterestGroup() throws Exception {
        //given
        InterestGroupDto 전자 = InterestGroupDto.builder()
                .name("전자")
                .sequence(1)
                .interests(new ArrayList<>())
                .memberId(memberId.toString())
                .build();

        InterestGroupDto 중공업 = InterestGroupDto.builder()
                .name("중공업")
                .sequence(2)
                .interests(new ArrayList<>())
                .memberId(memberId.toString())
                .build();

        InterestGroupDto it = InterestGroupDto.builder()
                .name("it")
                .sequence(3)
                .interests(new ArrayList<>())
                .memberId(memberId.toString())
                .build();

        //when
        interestGroupService.addInterestGroup(전자);
        interestGroupService.addInterestGroup(중공업);
        interestGroupService.addInterestGroup(it);

        //then
        LinkedHashSet<InterestGroupDto> interestGroups = interestGroupService.getInterestGroups(memberId.toString());

        assertThat(interestGroups.size()).isEqualTo(3);
        for (InterestGroupDto interestGroup : interestGroups) {
            assertThat(interestGroup.getMemberId()).isEqualTo(memberId.toString());
            System.out.println("interestGroup = " + interestGroup);
        }

    }

    @Test
    @DisplayName("관심종목 그룹 수정")
    void updateInterestGroup() throws Exception {
        //given
        InterestGroupDto 전자 = InterestGroupDto.builder()
                .name("전자")
                .sequence(1)
                .interests(new ArrayList<>())
                .memberId(memberId.toString())
                .build();

        InterestGroupDto 중공업 = InterestGroupDto.builder()
                .name("중공업")
                .sequence(2)
                .interests(new ArrayList<>())
                .memberId(memberId.toString())
                .build();

        InterestGroupDto it = InterestGroupDto.builder()
                .name("it")
                .sequence(3)
                .interests(new ArrayList<>())
                .memberId(memberId.toString())
                .build();

        interestGroupService.addInterestGroup(전자);
        interestGroupService.addInterestGroup(중공업);
        InterestGroupDto itDto = interestGroupService.addInterestGroup(it);

        //when
        InterestGroupUpdateDto updateDto = InterestGroupUpdateDto.builder()
                .name("not even it anymore")
                .sequence(5)
                .build();
        boolean b = interestGroupService.updateInterestGroup(itDto.get_id(), updateDto);
        LinkedHashSet<InterestGroupDto> interestGroups = interestGroupService.getInterestGroups(memberId.toString());

        //then
        assertTrue(b);
        assertThat(interestGroups.size()).isEqualTo(3);
        List<String> collect = interestGroups.stream().map(i -> i.getName()).collect(Collectors.toList());
        for (String s : collect) {
            assertThat(s).isNotEqualTo("it");
        }

    }

    @Test
    @DisplayName("관심종목 그룹 리스트 수정")
    void updateInterestGroup2() throws Exception {
        // given
        List<StockItem> stockItems = Arrays.asList(new StockItem("035720", "카카오"), new StockItem("323410", "카카오뱅크")
                , new StockItem("035420", "네이버"), new StockItem("017670", "SK텔레콤"));

        InterestGroupDto it = InterestGroupDto.builder()
                .name("it")
                .sequence(3)
                .interests(stockItems)
                .memberId(memberId.toString())
                .build();

        String groupId = interestGroupService.addInterestGroup(it).get_id();
        InterestGroup beforeUpdate = mongoTemplate.findById(new ObjectId(groupId), InterestGroup.class);

        assertThat(beforeUpdate.getMemberId()).isEqualTo(memberId);
        assertThat(beforeUpdate.getName()).isEqualTo("it");
        assertThat(beforeUpdate.getInterests().size()).isEqualTo(4);

        //when
        List<StockItem> updateItems = Arrays.asList(new StockItem("005930", "삼성전자"), new StockItem("000660", "SK하이닉스"));
        InterestGroupUpdateDto updateDto = InterestGroupUpdateDto.builder()
                .name("전자")
                .stockItems(updateItems)
                .sequence(1)
                .build();

        interestGroupService.updateInterestGroup(groupId, updateDto);

        //then
        InterestGroup afterUpdate = mongoTemplate.findById(groupId, InterestGroup.class);

        assertThat(afterUpdate.getMemberId()).isEqualTo(memberId);
        assertThat(afterUpdate.getName()).isEqualTo("전자");
        assertThat(afterUpdate.getInterests().size()).isEqualTo(4);
    }

    @Test
    @DisplayName("관심종목 그룹 리스트 수정")
    void updateInterestGroup3() throws Exception {
        // given
        List<StockItem> stockItems = Arrays.asList(new StockItem("035720", "카카오"), new StockItem("323410", "카카오뱅크")
                , new StockItem("035420", "네이버"), new StockItem("017670", "SK텔레콤"));

        InterestGroupDto it = InterestGroupDto.builder()
                .name("it")
                .sequence(3)
                .interests(stockItems)
                .memberId(memberId.toString())
                .build();

        String groupId = interestGroupService.addInterestGroup(it).get_id();
        InterestGroup beforeUpdate = mongoTemplate.findById(new ObjectId(groupId), InterestGroup.class);

        assertThat(beforeUpdate.getMemberId()).isEqualTo(memberId);
        assertThat(beforeUpdate.getName()).isEqualTo("it");
        assertThat(beforeUpdate.getInterests().size()).isEqualTo(4);

        //when
        List<StockItem> updateItems = Arrays.asList(new StockItem("005930", "삼성전자"), new StockItem("000660", "SK하이닉스"));
        interestGroupService.updateInterestsInGroups(groupId, updateItems);

        //then
        InterestGroup afterUpdate = mongoTemplate.findById(groupId, InterestGroup.class);

        assertThat(afterUpdate.getMemberId()).isEqualTo(memberId);
        assertThat(afterUpdate.getName()).isEqualTo("it");
        assertThat(afterUpdate.getInterests().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("관심종목 그룹 삭제")
    void deleteInterestGroup() throws Exception {
        // given
        InterestGroupDto 전자 = InterestGroupDto.builder()
                .name("전자")
                .sequence(1)
                .memberId(memberId.toString())
                .interests(new ArrayList<>())
                .build();

        InterestGroupDto 중공업 = InterestGroupDto.builder()
                .name("중공업")
                .sequence(2)
                .memberId(memberId.toString())
                .interests(new ArrayList<>())
                .build();

        InterestGroupDto it = InterestGroupDto.builder()
                .name("it")
                .sequence(3)
                .memberId(memberId.toString())
                .interests(new ArrayList<>())
                .build();

        interestGroupService.addInterestGroup(전자);
        String target = interestGroupService.addInterestGroup(중공업).get_id();
        interestGroupService.addInterestGroup(it);

        //when
        assertThat(interestGroupService.getInterestGroups(memberId.toString()).size()).isEqualTo(3);
        interestGroupService.deleteInterestGroup(target);

        //then
        assertThat(interestGroupService.getInterestGroups(memberId.toString()).size()).isEqualTo(2);

    }

    @Test
    void findInterestGroupsByMemberId() throws Exception {
        //given
        String memberId = member.get_id().toString();

        //when
        LinkedHashSet<InterestGroupDto> interestGroups =
                interestGroupService.getInterestGroups(memberId);

        //then
        interestGroups.stream().map(interestGroup -> "interestGroup = " + interestGroup.getName()).forEach(System.out::println);
    }

    @Test
    void getInterestGroup() throws Exception {
        //given
        String memberId = "62b1692848b07a611c0965fe";
        //when
        LinkedHashSet<InterestGroupDto> interestGroups = interestGroupService.getInterestGroups(memberId);

        //then
        for (InterestGroupDto interestGroup : interestGroups) {
            System.out.println("interestGroup = " + interestGroup);
        }

    }

}