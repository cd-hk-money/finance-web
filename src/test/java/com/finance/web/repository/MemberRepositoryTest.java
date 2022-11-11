package com.finance.web.repository;

import com.finance.web.domain.Member;
import com.finance.web.vo.Message;
import com.finance.web.vo.StockItem;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    @DisplayName("멤버 생성 및 조회")
    void createMemberAndFind() throws Exception {
        //given
        Member member = Member.builder()
                .email("test@admin.com")
                .password("1234")
                .username("홍길동")
                .nickname("임꺽정")
                .notifications(new HashSet<>())
                .subscription(false)
                .build();

        ObjectId memberId = memberRepository.save(member).get_id();

        //when
        Member findMember = memberRepository.findById(memberId).orElseThrow();

        //then
        assertThat(findMember.get_id()).isEqualTo(member.get_id());
        assertThat(findMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(findMember.getPassword()).isEqualTo(member.getPassword());
    }

    
    @Test
    @DisplayName("이메일 중복검사")
    void existsMemberByEmailEquals() throws Exception {
        //given
        Member member1 = Member.builder()
                .email("test@admin.com")
                .password("1234")
                .username("홍길동")
                .nickname("임꺽정")
                .notifications(new HashSet<>())
                .subscription(false)
                .build();

        ObjectId memberId = memberRepository.save(member1).get_id();

        Member member2 = Member.builder()
                .email("test@admin.com")
                .password("1234")
                .username("장발장")
                .nickname("장발산")
                .notifications(new HashSet<>())
                .subscription(false)
                .build();

        Member member3 = Member.builder()
                .email("test2@admin.com")
                .password("1234")
                .username("장발단")
                .nickname("장발산")
                .notifications(new HashSet<>())
                .subscription(false)
                .build();

        //when
        boolean b = memberRepository.existsMemberByEmailEquals(member2.getEmail());
        boolean b2 = memberRepository.existsMemberByEmailEquals(member3.getEmail());

        //then
        assertTrue(b);
        assertFalse(b2);
    }
    
    @Test
    @DisplayName("username 중복검사")
    void existsMemberByUsernameEquals() throws Exception {
        //given
        Member member1 = Member.builder()
                .email("test@admin.com")
                .password("1234")
                .username("홍길동")
                .nickname("임꺽정")
                .notifications(new HashSet<>())
                .subscription(false)
                .build();

        ObjectId memberId = memberRepository.save(member1).get_id();

        Member member2 = Member.builder()
                .email("test@admin.com")
                .password("1234")
                .username("홍길동")
                .nickname("장발산")
                .notifications(new HashSet<>())
                .subscription(false)
                .build();

        Member member3 = Member.builder()
                .email("test2@admin.com")
                .password("1234")
                .username("장발장")
                .nickname("장발산")
                .notifications(new HashSet<>())
                .subscription(false)
                .build();

        //when
        boolean b = memberRepository.existsMemberByUsernameEquals(member2.getUsername());
        boolean b2 = memberRepository.existsMemberByUsernameEquals(member3.getUsername());

        //then
        assertTrue(b);
        assertFalse(b2);
    }




    @Test
    @DisplayName("멤버 삭제")
    void deleteMember() throws Exception {
        //given
        Member member = Member.builder()
                .email("test@admin.com")
                .password("1234")
                .username("홍길동")
                .notifications(new HashSet<>())
                .subscription(false)
                .build();

        ObjectId memberId = memberRepository.save(member).get_id();
        //when
        memberRepository.deleteById(memberId);

        //then
        assertTrue(memberRepository.findById(memberId).isEmpty());
    }


    @Test
    @DisplayName("알림설정 가지고 있는 회원 조회")
    void findMemberNotificationContainsTest() throws Exception {

        //given
        Member member1 = Member.builder()
                .email("test@admin.com")
                .password("1234")
                .username("홍길동")
                .nickname("임꺽정")
                .notifications(new HashSet<>())
                .subscription(false)
                .build();

        Member member2 = Member.builder()
                .email("test2@admin.com")
                .password("1234")
                .username("임꺽정")
                .notifications(new HashSet<>())
                .subscription(false)
                .build();

        ObjectId id1 = memberRepository.save(member1).get_id();
        ObjectId id2 = memberRepository.save(member2).get_id();

        //when
        StockItem stockItem = new StockItem("005930", "삼성전자");
        memberRepository.pushItemToNotifications(id1, stockItem);
        memberRepository.pushItemToNotifications(id2, stockItem);

        //then
        List<Member> membersNotificationContains = memberRepository.findMembersFollowingStockItem(stockItem);
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
                .title("삼성전자 6만원 안착 :)")
                .content("영차 영차")
                .build();

        //when
        StockItem stockItem = new StockItem("005930", "삼성전자");
        boolean b = memberRepository.pushMessage(stockItem, message);

        //then
        assertTrue(b);
        List<Member> membersFollwingStockItem = memberRepository.findMembersFollowingStockItem(stockItem);
        for (Member member : membersFollwingStockItem) {
            assertTrue(member.getNotifications().contains(stockItem));
            assertTrue(member.getMessages().contains(message));
        }
    }

    @Test
    @DisplayName("알림 설정 추가")
    void addItemToNotifications() throws Exception {
        Member member = Member.builder()
                .email("test@admin.com")
                .password("1234")
                .username("홍길동")
                .nickname("임꺽정")
                .notifications(new HashSet<>())
                .subscription(false)
                .build();

        ObjectId memberId = memberRepository.save(member).get_id();

        //when

        StockItem stockItem = new StockItem("005930", "삼성전자");
        boolean b = memberRepository.pushItemToNotifications(memberId, stockItem);

        assertTrue(b);
    }

    @Test
    @DisplayName("알림 설정 종목 조회")
    void readItemFromNotifications() throws Exception {
        Member member = Member.builder()
                .email("test@admin.com")
                .password("1234")
                .username("홍길동")
                .nickname("임꺽정")
                .notifications(new HashSet<>())
                .subscription(false)
                .build();

        ObjectId memberId = memberRepository.save(member).get_id();

        StockItem stockItem = new StockItem("005930", "삼성전자");
        memberRepository.pushItemToNotifications(memberId, stockItem);

        HashSet<StockItem> notifications = memberRepository.findNotificationsByMemberId(memberId);
        assertFalse(notifications.isEmpty());
        notifications.stream().map(notification -> "notification = " + notification).forEach(System.out::println);
    }

    @Test
    @DisplayName("알림 설정 삭제")
    void deleteItemFromNotifications() throws Exception {
        Member member = Member.builder()
                .email("test@admin.com")
                .password("1234")
                .username("홍길동")
                .nickname("임꺽정")
                .notifications(new HashSet<>())
                .subscription(false)
                .build();

        ObjectId memberId = memberRepository.save(member).get_id();

        StockItem stockItem = new StockItem("005930", "삼성전자");
        memberRepository.pushItemToNotifications(memberId, stockItem);

        boolean b = memberRepository.deleteItemFromNotifications(memberId, stockItem);

        assertTrue(b);
        assertTrue(memberRepository.findNotificationsByMemberId(memberId).isEmpty());
    }

}