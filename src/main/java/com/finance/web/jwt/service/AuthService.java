package com.finance.web.jwt.service;

import com.finance.web.dto.MemberDto;
import com.finance.web.dto.MemberRequestDto;
import com.finance.web.dto.MemberResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<?> signUpMember(MemberRequestDto.SignUp request);

    ResponseEntity<?> login(MemberRequestDto.Login login);

    ResponseEntity<?> logout(MemberRequestDto.Logout logout);

    ResponseEntity<?> reissue(MemberRequestDto.Reissue reissue);

    boolean existsEmail(String email);

    boolean existsUsername(String username);


}
