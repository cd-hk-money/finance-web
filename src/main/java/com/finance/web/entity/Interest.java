package com.finance.web.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.PROTECTED;


@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Interest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long interestGroupId;

    @Column(nullable = false)
    private String stockCode;

    @Column(nullable = false)
    private Integer sequence;

    @Column
    private Boolean notification;

    @Builder
    public Interest(Long interestGroupId, String stockCode,
                    Integer sequence, Boolean notification) {
        this.interestGroupId = interestGroupId;
        this.stockCode = stockCode;
        this.sequence = sequence;
        this.notification = notification;
    }
}
