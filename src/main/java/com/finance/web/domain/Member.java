package com.finance.web.domain;

import com.finance.web.dto.MemberDto;
import com.finance.web.vo.Message;
import com.finance.web.vo.StockItem;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@Document(collection = "members")
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@ToString(of = {"_id", "email", "username"})
public class Member {

    @Id
    private ObjectId _id;

    private String email;
    private String password;
    private String username;
    private String nickname;
    private Boolean subscription;

    private HashSet<StockItem> notifications;
    private List<Message> messages;


    public MemberDto toDto() {
        return MemberDto.builder()
                .email(email)
                .username(username)
                .nickname(nickname)
                .subscription(subscription)
                .notifications(notifications)
                .messages(messages)
                .build();
    }

}
