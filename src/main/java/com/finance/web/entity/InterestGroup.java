package com.finance.web.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static lombok.AccessLevel.*;

@Getter
@Builder
@Document(collection = "interestGroups")
@AllArgsConstructor
@ToString(of = {"id", "name", "sequence"})
@NoArgsConstructor(access = PROTECTED)
public class InterestGroup {

    @Id
    private ObjectId id;

    private String name;
    private Integer sequence;

    private Member member;

}
