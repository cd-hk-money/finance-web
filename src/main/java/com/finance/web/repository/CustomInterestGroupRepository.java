package com.finance.web.repository;

import com.finance.web.domain.Interest;
import org.bson.types.ObjectId;

public interface CustomInterestGroupRepository {

    void addInterest(ObjectId interestGroupId, Interest item);

    void deleteInterest(ObjectId interestGroupId, Interest item);

}

