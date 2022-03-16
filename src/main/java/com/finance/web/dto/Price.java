package com.finance.web.dto;

import com.finance.web.dto.Stock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
@Builder
public class Price {

    private Stock stock;
    private Date date;
    private Integer changes;
    private Integer expectation;
    private Integer close;
    private Integer open;
    private Integer high;
    private Integer volume;
}
