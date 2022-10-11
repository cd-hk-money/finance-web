package com.finance.web.repository;

import com.finance.web.domain.Member;
import com.finance.web.vo.Message;
import com.finance.web.vo.StockItem;
import org.bson.types.ObjectId;

import java.util.HashSet;
import java.util.List;

public interface CustomMemberRepository {

    boolean pushMessage(StockItem stockItem, Message message);

    boolean pushItemToNotifications(ObjectId memberId, StockItem item);

    boolean deleteItemFromNotifications(ObjectId memberId, StockItem item);

    HashSet<StockItem> findItemInNotifications(ObjectId memberId);

    List<Member> findMembersFollwingStockItem(StockItem stockItem);

    HashSet<StockItem> findNotificationsByMemberId(ObjectId memberId);
}
