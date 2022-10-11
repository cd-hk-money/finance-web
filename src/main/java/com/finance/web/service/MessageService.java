package com.finance.web.service;

import com.finance.web.vo.Message;
import com.finance.web.vo.StockItem;
import org.bson.types.ObjectId;

import java.util.Set;

public interface MessageService {

    boolean sendMessageToMembers(StockItem stockItem, Message message);

    Set<ObjectId> getMemberNotifications(String stockCode);
}
