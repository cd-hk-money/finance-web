package com.finance.web.entity;

import com.finance.web.dto.InterestResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.PROTECTED;


@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Interest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long interestGroupId;

    @Column(nullable = false)
    private String stockCode;

    @Column(nullable = false)
    private String stockName;

    @Column(nullable = false)
    private Integer sequence;

    @Column
    private Boolean notification;

    public InterestResponseDto toResponseDto() {
        return InterestResponseDto.builder()
                .id(id)
                .stockCode(stockCode)
                .stockName(stockName)
                .sequence(sequence)
                .build();
    }

}
