package com.finance.web.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.bson.types.ObjectId;

@Getter
@Builder
@AllArgsConstructor
public class InterestEnrollRequestDto {

    private ObjectId interestGroupId;
    private String stockCode;
    private String stockName;

}
