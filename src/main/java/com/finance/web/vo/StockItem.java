package com.finance.web.vo;

import lombok.*;

import static lombok.AccessLevel.PROTECTED;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class StockItem {

    private String stockCode;
    private String stockName;

}
