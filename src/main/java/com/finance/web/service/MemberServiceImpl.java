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
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public MemberDto createMember(SignUpRequestDto requestDto) {
        return memberRepository.save(requestDto.toDocument()).toDto();
    }

//    public Boolean deleteMember()

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public boolean addItemToNotifications(String memberId, StockItem stockItem) {
        return memberRepository.pushItemToNotifications(toObjectId(memberId), stockItem);
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public HashSet<StockItem> getItemListFromNotifications(String memberId) {
        return memberRepository.findNotificationsByMemberId(toObjectId(memberId));
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public boolean deleteItemInNotifications(String memberId, StockItem stockItem) {
        return memberRepository.deleteItemFromNotifications(toObjectId(memberId), stockItem);
    }

    @Override
    public ObjectId toObjectId(String id) {
        return new ObjectId(id);
    }
}
