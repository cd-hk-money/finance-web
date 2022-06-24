package com.finance.web.domain;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;

import static lombok.AccessLevel.*;

@Getter
@Builder
@Document(collection = "members")
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@ToString(of = {"id", "email", "username"})
public class Member {

    @Id
    private ObjectId id;

    private String email;

    private String password;

    private String username;

    private Boolean subscription;

    private HashSet<String> notifications;

}
