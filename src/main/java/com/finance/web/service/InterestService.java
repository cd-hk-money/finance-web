package com.finance.web.service;

import com.finance.web.dto.InterestGroupDto;
import com.finance.web.dto.InterestEnrollRequestDto;
import com.finance.web.dto.InterestResponseDto;
import com.finance.web.dto.InterestUnenrollRequestDto;
import com.finance.web.entity.Interest;

import java.util.HashSet;

public interface InterestService {

    InterestResponseDto enroll(InterestEnrollRequestDto interestEnrollRequestDto);

    void unEnroll(InterestUnenrollRequestDto requestDto);

    HashSet<Interest> getInterests(InterestGroupDto interestGroupDto);

}
