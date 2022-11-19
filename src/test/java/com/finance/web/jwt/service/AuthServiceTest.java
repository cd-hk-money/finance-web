package com.finance.web.jwt.service;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class AuthServiceTest {

    //    @Test
//    @DisplayName("이메일 중복검사")
//    void isAvailableEmail() throws Exception {
//        //given
//        Member member1 = Member.builder()
//                .email("wkdwoo@kakao.com")
//                .password("1234")
//                .username("장길동")
//                .notifications(new HashSet<>())
//                .subscription(false)
//                .build();
//
//        Member member2 = Member.builder()
//                .email("wkdwoos@gmail.com")
//                .password("1234")
//                .username("홍길동")
//                .notifications(new HashSet<>())
//                .subscription(false)
//                .build();
//
//        //when
//        boolean expectFalse = memberService.isAvailableEmail(member1.getEmail());
//        boolean expectTrue = memberService.isAvailableEmail(member2.getEmail());
//
//        //then
//        assertThat(expectFalse).isFalse();
//        assertThat(expectTrue).isTrue();
//    }
//
//
//    @Test
//    @DisplayName("username 중복검사")
//    void isAvailableUsername() throws Exception {
//        //given
//        Member member1 = Member.builder()
//                .email("wkdwoos@gmail.com")
//                .password("1234")
//                .username("장재영")
//                .notifications(new HashSet<>())
//                .subscription(false)
//                .build();
//
//        Member member2 = Member.builder()
//                .email("wkdwoo@naver.com")
//                .password("1234")
//                .username("홍길동22222")
//                .notifications(new HashSet<>())
//                .subscription(false)
//                .build();
//
//        //when
//        boolean expectFalse = memberService.isAvailableUsername(member1.getUsername());
//        boolean expectTrue = memberService.isAvailableUsername(member2.getUsername());
//
//        //then
//        assertThat(expectFalse).isFalse();
//        assertThat(expectTrue).isTrue();
//    }
//
//
//    @Test
//    @DisplayName("회원가입")
//    void signUp() throws Exception {
////        //given
////        SignUpRequestDto requestDto = SignUpRequestDto.builder()
////                .email("wkdwoos@gmail.com")
////                .password("1234")
////                .username("장휴일")
////                .nickname("마이클조던")
////                .subscription(false)
////                .build();
////
////        //when
////        MemberDto memberDto = memberService.signUpMember(requestDto);
////
////        //then
////        assertThat(memberDto.getUsername()).isEqualTo(requestDto.getUsername());
////        assertThat(memberDto.getEmail()).isEqualTo(requestDto.getEmail());
////        assertThat(memberDto.getNickname()).isEqualTo(requestDto.getNickname());
////        assertThat(memberDto.getSubscription()).isEqualTo(requestDto.getSubscription());
//    }


}
