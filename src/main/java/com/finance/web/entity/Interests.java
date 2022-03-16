package com.finance.web.entity;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.*;

public class Interests {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String name;
    private Integer sequence;

    @ManyToOne(fetch = FetchType.LAZY)
    private List<InterestItem> interestItems;
}
