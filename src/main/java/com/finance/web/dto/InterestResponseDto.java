package com.finance.web.dto;

import lombok.*;

import static lombok.AccessLevel.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class InterestResponseDto {
    private Long id;
    private String stockCode;
    private String stockName;
    private Integer sequence;
    private Boolean notification;
}
