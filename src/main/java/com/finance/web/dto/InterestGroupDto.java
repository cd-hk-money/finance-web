package com.finance.web.dto;

import lombok.*;
import org.bson.types.ObjectId;

import static lombok.AccessLevel.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class InterestGroupDto {
    private ObjectId id;
    private String name;
    private Integer sequence;
    private ObjectId memberId;
}
