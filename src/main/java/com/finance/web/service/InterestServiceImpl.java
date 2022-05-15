package com.finance.web.service;

import com.finance.web.repository.InterestGroupRepository;
import com.finance.web.repository.InterestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InterestServiceImpl implements InterestService {

    private final InterestRepository interestRepository;
    private final InterestGroupRepository interestGroupRepository;

    public Long enroll(String stockCode, Long InterestGroupId) {

        return null;
    }

    public void unenroll() {

    }
}
