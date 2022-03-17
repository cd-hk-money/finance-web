package com.finance.web.entity;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.PROTECTED;


@Entity
@NoArgsConstructor(access = PROTECTED)
public class Interest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long interestListId;

    @Column(nullable = false)
    private String stockCode;

    @Column(nullable = false)
    private Integer sequence;

    @Column
    private Boolean notification;

    @Builder
    public Interest(Long interestListId, String stockCode,
                    Integer sequence, Boolean notification) {
        this.interestListId = interestListId;
        this.stockCode = stockCode;
        this.sequence = sequence;
        this.notification = notification;
    }
}
