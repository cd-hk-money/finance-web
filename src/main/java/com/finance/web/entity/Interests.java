package com.finance.web.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.List;

public class Interests {

    @Id
    @GeneratedValue
    private Long id;

    private Member member;

    private String name;
    private Integer sequence;

    private List<InterestItem> interestItems;
}
