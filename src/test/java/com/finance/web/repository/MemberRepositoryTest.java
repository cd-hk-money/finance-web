package com.finance.web.repository;

import com.finance.web.vo.StockItem;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    ObjectId memberId;

    @BeforeEach
    void init() {
        memberId = new ObjectId("62b1ad6c4511dc1e6b9afddf");
    }

    @Test
    @DisplayName("관심종목 추가")
    void add() throws Exception {
        StockItem stockItem = new StockItem("005930", "삼성전자");
        boolean b = memberRepository.pushItemToNotifications(memberId, stockItem);

        assertTrue(b);
    }

    @Test
    @DisplayName("관심종목 조회")
    void read() throws Exception {
        HashSet<StockItem> notifications = memberRepository.findNotificationsByMemberId(memberId);
        notifications.stream().map(notification -> "notification = " + notification).forEach(System.out::println);
    }

    @Test
    @DisplayName("관심종목 삭제")
    void delete() throws Exception {
        StockItem stockItem = new StockItem("005930", "삼성전자");
        boolean b = memberRepository.deleteItemFromNotifications(memberId, stockItem);

        assertTrue(b);
        assertTrue(memberRepository.findNotificationsByMemberId(memberId).isEmpty());
    }

}