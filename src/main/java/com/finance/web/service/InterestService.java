package com.finance.web.service;

import com.finance.web.dto.InterestGroupDto;
import com.finance.web.entity.Interest;

import java.util.HashSet;

public interface InterestService {

    Long enroll(String stockCode, Long InterestGroupId);

    void unEnroll();

    HashSet<Interest> getInterests(InterestGroupDto interestGroupDto);

}
