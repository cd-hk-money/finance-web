package com.finance.web.dto;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Builder
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
