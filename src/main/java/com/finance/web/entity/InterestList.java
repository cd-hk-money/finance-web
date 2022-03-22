package com.finance.web.entity;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@ToString(of = {"id", "name", "sequence"})
@NoArgsConstructor(access = PROTECTED)
public class InterestList {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;
    private Integer sequence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Builder
    public InterestList(String name, Integer sequence, Member member) {
        this.name = name;
        this.sequence = sequence;
        this.member = member;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void changeSequence(Integer sequence) {
        this.sequence = sequence;
    }

}
