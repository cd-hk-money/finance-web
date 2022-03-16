package com.finance.web.entity;

import com.finance.web.dto.Stock;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class InterestItem extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Stock stock;

    private Integer sequence;
    private Boolean notification;

}
