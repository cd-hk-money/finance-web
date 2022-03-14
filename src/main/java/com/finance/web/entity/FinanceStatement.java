package com.finance.web.entity;

import javax.persistence.OneToMany;
import java.util.Date;
public class FinanceStatement {

    private Long id;

    @OneToMany
    private Stock stock;
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
