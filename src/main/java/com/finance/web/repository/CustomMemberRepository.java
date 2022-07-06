package com.finance.web.repository;

import com.finance.web.vo.StockItem;
import org.bson.types.ObjectId;

import java.util.HashSet;

public interface CustomMemberRepository {

    boolean pushItemToNotifications(ObjectId memberId, StockItem item);

    boolean deleteItemFromNotifications(ObjectId memberId, StockItem item);

    HashSet<StockItem> findItemInNotifications(ObjectId memberId);

    HashSet<StockItem> findNotificationsByMemberId(ObjectId memberId);
}
