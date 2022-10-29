package com.finance.web.service;

import com.finance.web.domain.Member;
import com.finance.web.vo.StockItem;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.*;

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

    @AfterEach
    void afterEach() {
        mongoTemplate.remove(member);
    }

    @Test
    void add() throws Exception {
        //given
        String memberId = member.get_id().toString();
        StockItem stockItem = new StockItem("035720", "카카오");

        //when
        boolean acknowledged = memberService.addItemToNotifications(memberId, stockItem);

        //then
        assertThat(acknowledged).isTrue();
    }

    @Test
    void get() throws Exception {
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
    void delete() throws Exception {
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