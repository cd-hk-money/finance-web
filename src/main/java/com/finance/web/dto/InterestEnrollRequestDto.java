package com.finance.web.dto;


import com.finance.web.entity.Interest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.bson.types.ObjectId;

@Getter
@Builder
@AllArgsConstructor
public class InterestEnrollRequestDto {

    private String stockCode;
    private String stockName;
    private ObjectId interestGroupId;
    private Integer sequence;
    private Boolean notification;

}
