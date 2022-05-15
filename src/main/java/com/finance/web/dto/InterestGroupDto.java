package com.finance.web.dto;

import lombok.*;

import static lombok.AccessLevel.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class InterestGroupDto {
    private Long id;
    private String name;
    private Integer sequence;
    private Long memberId;
}
