package com.finance.web.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Stock {

    private String code;
    private String name;
    private String sector;

}
