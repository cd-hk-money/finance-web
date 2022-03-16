package com.finance.web.vo;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class FinanceStatement {

    private Long id;

    private String stockCode;
    
    private Date date;

    private long stocks;
    private long currentAssets;
    private long liability;
    private long equity;
    private long revenue;
    private long grossMargin;
    private long profit;
    private long ebit;
    private Score score;

}