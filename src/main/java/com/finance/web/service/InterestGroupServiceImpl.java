package com.finance.web.service;

import com.finance.web.domain.Interest;
import com.finance.web.dto.InterestGroupDto;
import com.finance.web.repository.InterestGroupRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

@Service
@RequiredArgsConstructor
public class InterestGroupServiceImpl implements InterestGroupService {

    private static InterestGroupRepository interestGroupRepository;

    @Override
    public LinkedHashSet<InterestGroupDto> getInterestGroups(String memberId) {

        return null;
    }

    @Override
    public InterestGroupDto addInterestGroup(String memberId) {
        return null;
    }

    @Override
    public InterestGroupDto updateInterestGroupName(String interestGroupId, String changeName) {
        return null;
    }

    @Override
    public LinkedHashSet<InterestGroupDto> changeGroupSequence(HashMap<String, Integer> items) {
        return null;
    }

    @Override
    public void deleteInterestGroup(String interestGroupId) {

    }

    @Override
    public Interest addInterest(String interest) {
        return null;
    }

    @Override
    public void popInterest(String interest) {

    }

    @Override
    public InterestGroupDto changeInterestSequenceInGroup(String interestGroup, HashSet<Interest> interests) {
        return null;
    }

    @Override
    public ObjectId toObjectId(String id) {
        return null;
    }
}
