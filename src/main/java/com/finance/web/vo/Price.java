package com.finance.web.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
@Builder
public class Price {

    private String stockCode;
    private Date date;
    private Integer changes;
    private Integer expectation;
    private Integer close;
    private Integer open;
    private Integer high;
    private Integer volume;
}
