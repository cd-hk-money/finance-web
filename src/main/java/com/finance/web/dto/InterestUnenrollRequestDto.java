package com.finance.web.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class InterestUnenrollRequestDto {
    private Long interestId;
    private Long interestGroupId;
    private Integer sequence;
}
