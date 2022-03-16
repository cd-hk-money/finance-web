package com.finance.web.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String username;

    @Column
    private Boolean subscribe;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<InterestList> interestList = new ArrayList<>();

    @Builder
    public Member(String email, String password, String username,
                  Boolean subscribe, List<InterestList> interestList) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.subscribe = subscribe;
        this.interestList = interestList;
    }

    public void changeUsername(String username) {
        this.username = username;
    }

}
