package com.finance.web.service;

import com.finance.web.domain.Interest;
import com.finance.web.domain.InterestGroup;
import com.finance.web.dto.InterestGroupDto;
import com.finance.web.repository.InterestGroupRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InterestGroupServiceImpl implements InterestGroupService {

    private final InterestGroupRepository interestGroupRepository;

    @Override
    public LinkedHashSet<InterestGroupDto> getInterestGroups(String memberId) {

        return interestGroupRepository.findInterestGroupsByMemberId(toObjectId(memberId));
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
        return new ObjectId(id);
    }
}
