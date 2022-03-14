package com.finance.web.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 50)
    private String username;

    @Column
    private Boolean subscribe;

    @Embedded
    private InterestList interestList;


    @Builder
    public Member(Long id, String password, String email, String username,
                  Boolean subscribe, InterestList interestList) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.username = username;
        this.subscribe = subscribe;
        this.interestList = interestList;
    }
}
