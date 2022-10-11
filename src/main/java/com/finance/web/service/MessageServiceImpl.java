package com.finance.web.service;

import com.finance.web.domain.Member;
import com.finance.web.repository.MemberRepository;
import com.finance.web.vo.Message;
import com.finance.web.vo.StockItem;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MemberRepository memberRepository;

    @Override
    public boolean sendMessageToMembers(StockItem stockItem, Message message) {
        return memberRepository.pushMessage(stockItem, message);
    }

    @Override
    public Set<ObjectId> getMemberNotifications(String stockCode) {
        return memberRepository.findMemberByNotificationsContaining(stockCode).stream().parallel()
                .map(Member::get_id).collect(Collectors.toSet());
    }

}
