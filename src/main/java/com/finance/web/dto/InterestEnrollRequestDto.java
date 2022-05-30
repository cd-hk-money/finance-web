package com.finance.web.dto;


import com.finance.web.entity.Interest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class InterestEnrollRequestDto {

    private String stockCode;
    private String stockName;
    private Long interestGroupId;
    private Integer sequence;
    private Boolean notification;

    public Interest toEntity() {
        return Interest.builder()
                .stockCode(stockCode)
                .stockName(stockName)
                .interestGroupId(interestGroupId)
                .sequence(sequence)
                .notification(notification)
                .build();
    }

}
