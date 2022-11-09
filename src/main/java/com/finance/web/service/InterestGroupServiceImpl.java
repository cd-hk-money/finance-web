package com.finance.web.service;

import com.finance.web.vo.StockItem;
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
        groupOptional.orElseThrow(() -> new NoSuchElementException());
        return interestGroupRepository.updateInterestGroup(toObjectId(interestGroupId), updateDto);
    }

    @Override
    public boolean deleteInterestGroup(String interestGroupId) {
        Optional<InterestGroup> groupOptional = interestGroupRepository.findById(toObjectId(interestGroupId));
        interestGroupRepository.delete(groupOptional.orElseThrow(() -> new NoSuchElementException()));
        return interestGroupRepository.findById(toObjectId(interestGroupId)).isEmpty();
    }

    @Override
    public boolean addInterest(String interestGroupId, StockItem stockItem) {
        return interestGroupRepository.addInterestToGroup(toObjectId(interestGroupId), stockItem);
    }

    @Override
    public boolean popInterest(String interestGroupId, StockItem stockItem) {
        return interestGroupRepository.deleteInterestFromGroup(toObjectId(interestGroupId), stockItem);
    }

    @Override
    public InterestGroupDto updateInterestsInGroups(String interestGroupId, List<StockItem> interests) {
        return interestGroupRepository.updateInterests(toObjectId(interestGroupId), interests);
    }

    @Override
    public ObjectId toObjectId(String id) {
        return new ObjectId(id);
    }
}
