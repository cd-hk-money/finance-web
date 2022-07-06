package com.finance.web.service;

import com.finance.web.vo.StockItem;
import org.bson.types.ObjectId;

import java.util.HashSet;

public interface MemberService {

    boolean addItemToNotifications(String memberId, StockItem stockItem);

    HashSet<StockItem> getItemListFromNotifications(String memberId);

    boolean deleteItemInNotifications(String memberId, StockItem stockItem);

    ObjectId toObjectId(String id);
}
