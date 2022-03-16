package com.finance.web.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.*;

@Embeddable
@NoArgsConstructor(access = PROTECTED)
public class InterestList {

    @OneToMany(mappedBy = "member")
    private List<Interests> interestsList = new ArrayList<>();

    public void setInterests(List<Interests> interestsList) {
        this.interestsList.addAll(interestsList);
    }

}
