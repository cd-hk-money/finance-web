package com.finance.web.jwt.controller;

import com.finance.web.dto.MemberRequestDto;
import com.finance.web.dto.Response;
import com.finance.web.jwt.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final Response response;

    @GetMapping("/email/{email}")
    public ResponseEntity<?> checkAvailableEmail(@PathVariable String email) {
        return authService.existsEmail(email) ? response.fail("This email is already in use", email, HttpStatus.CONFLICT) :
                response.success("This email is available", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> checkAvailableUsername(@PathVariable String username) {
        return authService.existsUsername(username) ? response.fail("This username is already in use", username, HttpStatus.CONFLICT) :
                response.success("This username is available", HttpStatus.NO_CONTENT);
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody MemberRequestDto.SignUp request) {
        return authService.signUpMember(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberRequestDto.Login request) {
        return authService.login(request);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody MemberRequestDto.Logout request) {
        return authService.logout(request);
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@RequestBody MemberRequestDto.Reissue request) {
        return authService.reissue(request);
    }

}
