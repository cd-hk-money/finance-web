package com.finance.web.service;

import com.finance.web.dto.MemberDto;
import com.finance.web.dto.SignUpRequestDto;
import com.finance.web.vo.StockItem;
import org.bson.types.ObjectId;

import java.util.HashSet;

public interface MemberService {

    MemberDto signUpMember(SignUpRequestDto requestDto);

    public boolean isAvailableEmail(String email);

    public boolean isAvailableUsername(String username);

    boolean addItemToNotifications(String memberId, StockItem stockItem);

    HashSet<StockItem> getItemListFromNotifications(String memberId);

    boolean deleteItemInNotifications(String memberId, StockItem stockItem);

    ObjectId toObjectId(String id);
}
