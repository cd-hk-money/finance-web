package com.finance.web.jwt.service;

import com.finance.web.dto.MemberDto;
import com.finance.web.dto.MemberRequestDto;
import com.finance.web.dto.MemberResponseDto;

public interface AuthService {

    MemberDto signUpMember(MemberRequestDto.SignUp request);

    MemberResponseDto.TokenInfo login(MemberRequestDto.Login login);

    MemberResponseDto.TokenInfo reissue(MemberRequestDto.Reissue reissue);

    boolean isAvailableEmail(String email);

    boolean isAvailableUsername(String username);


}
