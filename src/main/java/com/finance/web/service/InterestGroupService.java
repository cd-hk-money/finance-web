package com.finance.web.service;

import com.finance.web.domain.Interest;
import com.finance.web.dto.InterestGroupDto;
import com.finance.web.dto.InterestGroupUpdateDto;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public interface InterestGroupService {

    // 반환할 때 sort by sequence로 하는게 나을까? 성능고민.
    LinkedHashSet<InterestGroupDto> getInterestGroups(String memberId);

    InterestGroupDto addInterestGroup(InterestGroupDto interestGroupDto);

    boolean updateInterestGroup(String interestGroupId, InterestGroupUpdateDto updateDto);

    boolean deleteInterestGroup(String interestGroupId);

    boolean addInterest(String interestGroupId, Interest interest);

    boolean popInterest(String interestGroupId, Interest interest);

    InterestGroupDto changeInterestsSequenceInGroup(String interestGroupId, List<Interest> interests);

    ObjectId toObjectId(String id);

}
