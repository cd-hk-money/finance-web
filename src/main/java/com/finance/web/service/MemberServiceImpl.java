package com.finance.web.service;

import com.finance.web.dto.MemberDto;
import com.finance.web.dto.SignUpRequestDto;
import com.finance.web.repository.MemberRepository;
import com.finance.web.vo.StockItem;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public MemberDto signUpMember(SignUpRequestDto requestDto) {
        if (isAvailableEmail(requestDto.getEmail()) && isAvailableUsername(requestDto.getUsername())) {
            return memberRepository.save(requestDto.toDocument()).toDto();
        }

        throw new IllegalArgumentException("회원 등록에 실패했어요 :(");
    }

    @Override
    public boolean isAvailableEmail(String email) {
        return !memberRepository.existsMemberByEmailEquals(email);
    }

    @Override
    public boolean isAvailableUsername(String username) {
        return !memberRepository.existsMemberByUsernameEquals(username);
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
