package com.finance.web.service;

import com.finance.web.vo.StockItem;
import com.finance.web.dto.InterestGroupDto;
import com.finance.web.dto.InterestGroupUpdateDto;
import org.bson.types.ObjectId;

import java.util.LinkedHashSet;
import java.util.List;

public interface InterestGroupService {

    // 반환할 때 sort by sequence로 하는게 나을까? 성능고민.
    LinkedHashSet<InterestGroupDto> getInterestGroups(String memberId);

    InterestGroupDto addInterestGroup(InterestGroupDto interestGroupDto);

    boolean updateInterestGroup(String interestGroupId, InterestGroupUpdateDto updateDto);

    boolean deleteInterestGroup(String interestGroupId);

    boolean addInterest(String interestGroupId, StockItem stockItem);

    boolean popInterest(String interestGroupId, StockItem stockItem);

    InterestGroupDto changeInterestsSequenceInGroup(String interestGroupId, List<StockItem> stockItems);

    ObjectId toObjectId(String id);

}
