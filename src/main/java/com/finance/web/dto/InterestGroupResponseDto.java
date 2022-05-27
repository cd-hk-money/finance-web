package com.finance.web.dto;

import lombok.*;

import java.util.HashSet;

import static lombok.AccessLevel.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class InterestGroupResponseDto {

    private Long id;
    private String name;
    private HashSet<InterestResponseDto> item;

}
