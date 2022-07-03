package com.finance.web.repository;

import com.finance.web.vo.StockItem;
import com.finance.web.dto.InterestGroupDto;
import com.finance.web.dto.InterestGroupUpdateDto;
import org.bson.types.ObjectId;

import java.util.List;


public interface CustomInterestGroupRepository {

    boolean addInterestToGroup(ObjectId interestGroupId, StockItem item);

    boolean deleteInterestFromGroup(ObjectId interestGroupId, StockItem item);

    boolean updateInterestGroup(ObjectId interestGroupId, InterestGroupUpdateDto updateDto);

    InterestGroupDto updateInterests(ObjectId interestGroupId, List<StockItem> stockItems);
}

