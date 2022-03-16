package com.finance.web.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

import static lombok.AccessLevel.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Indicator {

    private String stockCode;
    private Date date;
    private double eps;
    private double per;
    private double bps;
    private double pbr;
    private double roe;
    private double pcr;
    private double ev_ebitda;
    private double pegr;
    private double dr;
}
