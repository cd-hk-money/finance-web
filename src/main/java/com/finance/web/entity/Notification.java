package com.finance.web.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String stockCode;

    private String title;

    private String sentence;


}
