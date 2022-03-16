package com.finance.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.*;

@Getter
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
public class Stock {

    private String code;
    private String name;
    private String sector;
    private Prices prices;

    //@OneToMany
    // private List<FinanceStatement> financeStatements;

    // @OneToMany
    // private Indicators indicators;



}
