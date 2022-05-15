package com.finance.web.service;


public interface InterestGroupService {

    public Long create(Long memberId, String groupName, Integer sequence);

    public void delete(Long groupId);

    public void updateName(Long interestGroupId, String name);

    public void changeSequence(Integer sequence);
}
