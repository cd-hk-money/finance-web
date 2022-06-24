package com.finance.web.repository;

import org.bson.types.ObjectId;

public interface CustomMemberRepository {

    void pushItemToNotifications(ObjectId memberId, String item);

    void deleteItemFromNotifications(ObjectId memberId, String item);

}
