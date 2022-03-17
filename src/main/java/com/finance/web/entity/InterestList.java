package com.finance.web.entity;

import lombok.ToString;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Entity
@ToString(of = {"id", "name", "sequence"})
public class InterestList {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;
    private Integer sequence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

}
