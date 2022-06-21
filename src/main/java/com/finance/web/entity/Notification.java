package com.finance.web.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static lombok.AccessLevel.*;

@Getter
@Builder
@Document(collection = "notifications")
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Notification {

    @Id
    private ObjectId id;

    private ObjectId memberId;

    private String stockCode;

    private String title;

    private String sentence;

}
