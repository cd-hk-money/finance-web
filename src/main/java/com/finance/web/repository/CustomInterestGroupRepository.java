package com.finance.web.repository;

import com.finance.web.domain.Interest;
import com.finance.web.dto.InterestGroupDto;
import org.bson.types.ObjectId;

import java.util.LinkedHashSet;

public interface CustomInterestGroupRepository {

    void addInterest(ObjectId interestGroupId, Interest item);

    void deleteInterest(ObjectId interestGroupId, Interest item);

    LinkedHashSet<InterestGroupDto> findByMemberId(ObjectId memberId);
}

