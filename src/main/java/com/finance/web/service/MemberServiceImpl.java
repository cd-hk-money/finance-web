package com.finance.web.service;

import com.finance.web.repository.MemberRepository;
import com.finance.web.vo.StockItem;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

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
