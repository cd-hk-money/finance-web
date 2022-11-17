package com.finance.web.jwt.controller;

import com.finance.web.dto.MemberDto;
import com.finance.web.dto.MemberRequestDto;
import com.finance.web.jwt.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.finance.web.dto.MemberResponseDto.TokenInfo;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @GetMapping("/email/{email}")
    public ResponseEntity checkAvailableEmail(@PathVariable("email") String email) {
        return authService.isAvailableEmail(email) ? new ResponseEntity(HttpStatus.NO_CONTENT) : new ResponseEntity(HttpStatus.CONFLICT);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity checkAvailableUsername(@PathVariable("username") String username) {
        return authService.isAvailableUsername(username) ? new ResponseEntity(HttpStatus.NO_CONTENT) : new ResponseEntity(HttpStatus.CONFLICT);
    }

    @PostMapping("/signUp")
    public ResponseEntity<MemberDto> signUp(@RequestBody MemberRequestDto.SignUp request) {
        MemberDto memberDto = authService.signUpMember(request);
        return new ResponseEntity<MemberDto>(memberDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenInfo> login(@RequestBody MemberRequestDto.Login request) {
        TokenInfo login = authService.login(request);
        return new ResponseEntity<TokenInfo>(login, HttpStatus.CREATED);
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenInfo> reissue(@RequestBody MemberRequestDto.Reissue reissue) {
        return new ResponseEntity<TokenInfo>(authService.reissue(reissue), HttpStatus.CREATED);
    }

}
