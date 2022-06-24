package com.finance.web.repository;

import com.finance.web.domain.Interest;
import com.finance.web.dto.InterestGroupDto;
import com.finance.web.dto.InterestGroupUpdateDto;
import org.bson.types.ObjectId;

import java.util.List;


public interface CustomInterestGroupRepository {

    boolean addInterestToGroup(ObjectId interestGroupId, Interest item);

    boolean deleteInterestFromGroup(ObjectId interestGroupId, Interest item);

    boolean updateInterestGroup(ObjectId interestGroupId, InterestGroupUpdateDto updateDto);

    InterestGroupDto updateInterests(ObjectId interestGroupId, List<Interest> interests);
}

