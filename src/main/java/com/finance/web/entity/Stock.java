package com.finance.web.entity;

import javax.persistence.OneToMany;
import java.util.List;

public class Stock {

    private String code;
    private String name;
    private String sector;

    @OneToMany
    private Prices prices;

    @OneToMany
    private List<FinanceStatement> financeStatements;

    @OneToMany
    private Indicators indicators;

}
