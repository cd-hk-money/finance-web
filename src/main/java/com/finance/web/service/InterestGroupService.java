package com.finance.web.service;

import com.finance.exception.service.NoSuchIdException;

public interface InterestGroupService {

    public Long create(Long memberId, String groupName, Integer sequence) throws NoSuchIdException;

    public void delete(Long groupId);

    public void updateName(Long interestGroupId, String name);

    public void changeSequence(Integer sequence);
}
