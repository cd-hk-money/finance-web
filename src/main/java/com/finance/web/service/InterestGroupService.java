package com.finance.web.service;

import com.finance.web.domain.Interest;
import com.finance.web.dto.InterestGroupDto;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

public interface InterestGroupService {


    // 반환할 때 sort by sequence로 하는게 나을까? 성능고민.
    LinkedHashSet<InterestGroupDto> getInterestGroups(String memberId);

    InterestGroupDto addInterestGroup(InterestGroupDto interestGroupDto);

    InterestGroupDto updateInterestGroupName(String interestGroupId, String changeName);

    LinkedHashSet<InterestGroupDto> changeGroupSequence(HashMap<String, Integer> items);

    void deleteInterestGroup(String interestGroupId);

    Interest addInterest(String interest);

    void popInterest(String interest);

    // 지금의 생각은 save-update
    InterestGroupDto changeInterestSequenceInGroup(String interestGroup, HashSet<Interest> interests);

    ObjectId toObjectId(String id);

}
