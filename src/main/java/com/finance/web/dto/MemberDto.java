package com.finance.web.dto;

import lombok.Data;

@Data
public class MemberDto {
    private String email;
    private String username;
    private Boolean subscribe;
}
