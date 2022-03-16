package com.finance.web.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;


@Entity
public class Interest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    private Long interestListId;

    @Column(nullable = false)
    private String stockCode;

    @Column
    private Integer sequence;

    @Column
    private Boolean notification;

}
