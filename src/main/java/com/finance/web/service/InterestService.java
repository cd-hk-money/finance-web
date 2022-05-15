package com.finance.web.service;

public interface InterestService {

    public Long enroll(String stockCode, Long InterestGroupId);

    public void unenroll();

}
