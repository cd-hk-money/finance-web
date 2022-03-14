package com.finance.web.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.List;

import static lombok.AccessLevel.*;

@Embeddable
@NoArgsConstructor(access = PROTECTED)
public class InterestList {

    private List<Interests> interests;

    public InterestList(List<Interests> interests) {
        this.interests = interests;
    }
}
