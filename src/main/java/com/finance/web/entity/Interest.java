package com.finance.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static lombok.AccessLevel.PROTECTED;


@Builder
@Getter
@Document(collection = "interests")
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Interest {


    @Id
    private ObjectId id;

    private ObjectId interestGroupId;

    private String stockCode;

    private String stockName;

    private Integer sequence;

    private Boolean notification;

}
