package com.finance.web.service;

import com.finance.web.domain.Member;
import com.finance.web.jwt.service.UserDetailsImpl;
import com.finance.web.repository.MemberRepository;
import com.finance.web.vo.StockItem;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService, UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findMemberByUsername(username).orElseThrow(()
                -> new UsernameNotFoundException("존재하지 않는 회원이에요!"));

        return new UserDetailsImpl(member);
    }

    // 해당하는 User 의 데이터가 존재한다면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(UserDetailsImpl users) {
        return new User(users.getUsername(), users.getPassword(), users.getAuthorities());
    }


    @Override
    public boolean addItemToNotifications(String memberId, StockItem stockItem) {
        return memberRepository.pushItemToNotifications(toObjectId(memberId), stockItem);
    }

    @Override
    public HashSet<StockItem> getItemListFromNotifications(String memberId) {
        return memberRepository.findNotificationsByMemberId(toObjectId(memberId));
    }

    @Override
    public boolean deleteItemInNotifications(String memberId, StockItem stockItem) {
        return memberRepository.deleteItemFromNotifications(toObjectId(memberId), stockItem);
    }

    @Override
    public ObjectId toObjectId(String id) {
        return new ObjectId(id);
    }
}
