package com.finance.web.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Message {

    private String createdDate;
    private String title;
    private String content;
    private StockItem stockItem;
    private Boolean isChecked;

    public Boolean checkMessage() {
        this.isChecked = Boolean.TRUE;
        return Boolean.TRUE;
    }
}
