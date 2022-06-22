package com.finance.web.domain;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import static lombok.AccessLevel.*;

@Getter
@Builder
@Document(collection = "interestGroups")
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class InterestGroup {

    @Id
    private ObjectId id;

    private String name;
    private Integer sequence;
    private List<Interest> interests;
    private ObjectId memberId;

}
