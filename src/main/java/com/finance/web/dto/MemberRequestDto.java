package com.finance.web.dto;


import com.finance.web.domain.Member;
import com.finance.web.vo.Message;
import com.finance.web.vo.StockItem;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.HashSet;

public class MemberRequestDto {

    @Getter
    @Setter
    @ToString
    public static class SignUp {
        @NotEmpty(message = "이메일은 필수 입력값입니다.")
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
        private String email;

        @NotEmpty(message = "username은 필수 입력값입니다.")
        @Pattern(regexp = "^[A-Za-z0-9._%+-]$", message = "username 형식에 맞지 않습니다.")
        private String username;

        @NotEmpty(message = "nickname은 필수 입력값입니다.")
        @Pattern(regexp = "^[A-Za-z0-9._%+-]$", message = "nickname 형식에 맞지 않습니다.")
        private String nickname;

        @NotEmpty(message = "비밀번호는 필수 입력값입니다.")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
        private String password;

    }

    @Getter
    @Setter
    @ToString
    public static class Login {
        @NotEmpty(message = "username은 필수 입력값입니다.")
        @Pattern(regexp = "^[A-Za-z0-9._%+-]$", message = "username 형식에 맞지 않습니다.")
        private String username;

        @NotEmpty(message = "비밀번호는 필수 입력값입니다.")
        private String password;

        public UsernamePasswordAuthenticationToken toAuthentication() {
            return new UsernamePasswordAuthenticationToken(username, password);
        }
    }

    @Getter
    @Setter
    public static class Reissue {
        private String accessToken;
        private String refreshToken;
    }

    @Getter
    @Setter
    public static class Logout {
        @NotEmpty(message = "잘못된 요청입니다.")
        private String accessToken;

        @NotEmpty(message = "잘못된 요청입니다.")
        private String refreshToken;
    }


}
