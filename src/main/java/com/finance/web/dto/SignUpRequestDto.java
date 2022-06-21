package com.finance.web.dto;

import com.finance.web.entity.Member;
import lombok.*;

import static lombok.AccessLevel.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class SignUpRequestDto {

    private String email;
    private String username;
    private String password;
    private Boolean subscription;

}
