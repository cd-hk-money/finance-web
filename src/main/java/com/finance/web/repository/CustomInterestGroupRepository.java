package com.finance.web.repository;

import com.finance.web.entity.Interest;
import org.bson.types.ObjectId;

public interface CustomInterestGroupRepository {

    void addInterest(ObjectId interestGroupId, Interest item);

    void deleteInterest(ObjectId interestGroupId, Interest item);

}

