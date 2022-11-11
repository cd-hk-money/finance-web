package com.finance.web.dto;

import com.finance.web.domain.Member;
import com.finance.web.vo.Message;
import com.finance.web.vo.StockItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class SignUpRequestDto {

    private String email;
    private String username;
    private String nickname;
    private String password;
    private Boolean subscription;

    public Member toDocument() {
        return Member.builder()
                .email(email)
                .username(username)
                .nickname(nickname)
                .password(password)
                .messages(new ArrayList<Message>())
                .notifications(new HashSet<StockItem>())
                .subscription(false)
                .build();
    }

}
