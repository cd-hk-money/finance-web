package com.finance.web.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.bson.types.ObjectId;

@Getter
@Builder
@AllArgsConstructor
public class InterestUnenrollRequestDto {
    private ObjectId interestId;
    private ObjectId interestGroupId;
    private Integer sequence;
}