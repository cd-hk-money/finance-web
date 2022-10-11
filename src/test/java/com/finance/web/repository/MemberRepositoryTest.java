package com.finance.web.repository;

import com.finance.web.domain.Member;
import com.finance.web.vo.Message;
import com.finance.web.vo.StockItem;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    @DisplayName("관심종목 가지고 있는 회원 조회")
    void findMemberNotificationContainsTest() throws Exception {
        StockItem stockItem = new StockItem("005930", "삼성전자");
        List<Member> membersNotificationContains = memberRepository.findMembersFollwingStockItem(stockItem);

        assertThat(membersNotificationContains.size()).isNotZero();

        for (Member member : membersNotificationContains) {
            System.out.println("member = " + member.getNotifications().toString());
            assertTrue(member.getNotifications().contains(stockItem));
        }
    }

    @Test
    @DisplayName("메시지 생성 테스트")
    void pushMessageTest() throws Exception {
        //given
        LocalDateTime now = LocalDateTime.now();
        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
        Message message = Message.builder()
                .createdDate(formatedNow)
                .stockItem(new StockItem("005930", "삼성전자"))
                .title("삼성전자 3만원 떡락 :(")
                .content("영차 영차")
                .build();

        //when
        StockItem stockItem = new StockItem("005930", "삼성전자");
        boolean b = memberRepository.pushMessage(stockItem, message);

        //then
        assertTrue(b);
        List<Member> membersFollwingStockItem = memberRepository.findMembersFollwingStockItem(stockItem);
        for (Member member : membersFollwingStockItem) {
            assertTrue(member.getNotifications().contains(stockItem));
            assertTrue(member.getMessages().contains(message));
        }
    }

    @Test
    @DisplayName("관심종목 추가")
    void add() throws Exception {
        memberId = new ObjectId("62b1692848b07a611c0965fe");
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