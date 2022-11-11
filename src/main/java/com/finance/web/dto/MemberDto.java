package com.finance.web.dto;

import com.finance.web.vo.Message;
import com.finance.web.vo.StockItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class MemberDto {

    private String email;
    private String username;
    private String nickname;
    private Boolean subscription;
    private HashSet<StockItem> notifications;
    List<Message> messages;

}
