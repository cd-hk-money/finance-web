package com.finance.web.service;

import com.finance.web.domain.Interest;
import com.finance.web.domain.InterestGroup;
import com.finance.web.dto.InterestGroupDto;
import com.finance.web.dto.InterestGroupUpdateDto;
import com.finance.web.repository.InterestGroupRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class InterestGroupServiceImpl implements InterestGroupService {

    private final InterestGroupRepository interestGroupRepository;

    @Override
    public LinkedHashSet<InterestGroupDto> getInterestGroups(String memberId) {

        return interestGroupRepository.findInterestGroupsByMemberId(toObjectId(memberId));
    }

    @Override
    public InterestGroupDto addInterestGroup(InterestGroupDto interestGroupDto) {

        return interestGroupRepository.save(interestGroupDto.toDocument()).toDto();
    }

    @Override
    public boolean updateInterestGroup(String interestGroupId, InterestGroupUpdateDto updateDto) {
        Optional<InterestGroup> groupOptional = interestGroupRepository.findById(toObjectId(interestGroupId));
        if (groupOptional.isPresent())
            return interestGroupRepository.updateInterestGroup(toObjectId(interestGroupId), updateDto);
        return false;
    }

    @Override
    public boolean deleteInterestGroup(String interestGroupId) {
        Optional<InterestGroup> groupOptional = interestGroupRepository.findById(toObjectId(interestGroupId));
        if (groupOptional.isPresent()) {
            interestGroupRepository.delete(groupOptional.get());
            return interestGroupRepository.findById(toObjectId(interestGroupId)).isEmpty();
        }
        return false;
    }

    @Override
    public boolean addInterest(String interestGroupId, Interest interest) {
        return interestGroupRepository.addInterestToGroup(toObjectId(interestGroupId), interest);
    }

    @Override
    public boolean popInterest(String interestGroupId, Interest interest) {
        return interestGroupRepository.deleteInterestFromGroup(toObjectId(interestGroupId), interest);
    }

    @Override
    public InterestGroupDto changeInterestsSequenceInGroup(String interestGroupId, List<Interest> interests) {
        return interestGroupRepository.updateInterests(toObjectId(interestGroupId), interests);
    }

    @Override
    public ObjectId toObjectId(String id) {
        return new ObjectId(id);
    }
}
