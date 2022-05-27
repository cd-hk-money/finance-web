package com.finance.web.service;


import com.finance.web.dto.InterestGroupDto;
import com.finance.web.dto.InterestGroupResponseDto;

import java.util.List;

public interface InterestGroupService {

    InterestGroupDto create(Long memberId, InterestGroupDto interestGroupDto);

    void delete(Long groupId);

    void updateName(Long interestGroupId, String name);

    void changeSequence(Integer sequence);

    List<InterestGroupResponseDto> getInterestGroups(Long memberId);
}
