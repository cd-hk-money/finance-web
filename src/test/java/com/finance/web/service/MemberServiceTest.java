package com.finance.web.service;

import com.finance.web.domain.Member;
import com.finance.web.dto.MemberDto;
import com.finance.web.dto.SignUpRequestDto;
import com.finance.web.vo.StockItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MongoTemplate mongoTemplate;

    Member member;

    @BeforeEach
    void beforeEach() {
        member = Member.builder()
                .email("wkdwoo@kakao.com")
                .password("1234")
                .username("장재영")
                .notifications(new HashSet<>())
                .subscription(false)
                .build();

        mongoTemplate.save(member);
    }


    @Test
    @DisplayName("알림설정 아이템 추가")
    void addStockItem() throws Exception {
        //given
        String memberId = member.get_id().toString();
        StockItem stockItem = new StockItem("035720", "카카오");

        //when
        boolean acknowledged = memberService.addItemToNotifications(memberId, stockItem);

        //then
        assertThat(acknowledged).isTrue();
    }

    @Test
    @DisplayName("알림설정 아이템 조회")
    void getNotificationList() throws Exception {
        //given
        String memberId = member.get_id().toString();
        StockItem stockItem = new StockItem("035720", "카카오");
        StockItem stockItem2 = new StockItem("035420", "네이버");
        StockItem stockItem3 = new StockItem("005930", "삼성전자");
        for (StockItem item : Arrays.asList(stockItem, stockItem2, stockItem3))
            memberService.addItemToNotifications(memberId, item);

        //when
        HashSet<StockItem> itemListFromNotifications = memberService.getItemListFromNotifications(memberId);

        //then
        itemListFromNotifications.stream().map(itemListFromNotification -> "itemListFromNotification = " + itemListFromNotification).forEach(System.out::println);
    }

    @Test
    @DisplayName("알림설정 아이템 삭제")
    void deleteItemInNotifications() throws Exception {
        //given
        String memberId = member.get_id().toString();
        StockItem stockItem = new StockItem("035720", "카카오");
        memberService.addItemToNotifications(memberId, stockItem);

        //when
        boolean acknowledged = memberService.deleteItemInNotifications(memberId, stockItem);

        //then
        assertThat(acknowledged).isTrue();

    }

}