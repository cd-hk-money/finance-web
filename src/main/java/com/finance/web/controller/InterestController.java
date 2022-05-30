package com.finance.web.controller;

import com.finance.web.service.InterestGroupService;
import com.finance.web.service.InterestGroupServiceImpl;
import com.finance.web.service.InterestService;
import com.finance.web.service.InterestServiceImpl;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InterestController {
    private final InterestService interestService;
    private final InterestGroupService interestGroupService;

    public InterestController(InterestServiceImpl interestService, InterestGroupServiceImpl interestGroupService) {
        this.interestService = interestService;
        this.interestGroupService = interestGroupService;
    }


}
