package com.finance.web.jwt.service;

import com.finance.web.domain.Member;
import com.finance.web.dto.MemberRequestDto;
import com.finance.web.dto.MemberResponseDto;
import com.finance.web.dto.Response;
import com.finance.web.exception.NotExistUserException;
import com.finance.web.jwt.utils.JwtTokenProvider;
import com.finance.web.repository.MemberRepository;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final Response response;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate redisTemplate;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Override
    @Transactional
    public ResponseEntity<?> signUpMember(MemberRequestDto.SignUp request) {
        if (existsEmail(request.getEmail())) {
            return response.fail("This email is already in use", HttpStatus.CONFLICT);
        }
        if (existsEmail(request.getUsername())) {
            return response.fail("This username is already in use", HttpStatus.CONFLICT);
        }

        Member member = Member.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("USER")
                .nickname(request.getNickname())
                .subscription(false)
                .messages(new ArrayList<>())
                .notifications(new HashSet<>())
                .build();

        return response.success(memberRepository.save(member).toDto(), "Sign up completed", HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<?> login(MemberRequestDto.Login login) {
        if (!existsUsername(login.getUsername())) {
            response.fail("Member who does not exist.", HttpStatus.NOT_FOUND);
        }
        Member member = memberRepository.findMemberByUsername(login.getUsername()).orElseThrow(()
                -> new NotExistUserException("Member who does not exist."));

        UsernamePasswordAuthenticationToken authenticationToken = login.toAuthentication();
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        MemberResponseDto.TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(), tokenInfo.getRefreshToken(), tokenInfo.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

        return response.success(tokenInfo, "Login successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> reissue(MemberRequestDto.Reissue reissue) {
        if (!jwtTokenProvider.validateToken(reissue.getRefreshToken())) {
            return response.fail("Refresh Token is invalid", HttpStatus.BAD_REQUEST);
        }

        Authentication authentication = jwtTokenProvider.getAuthentication(reissue.getAccessToken());
        String refreshToken = (String) redisTemplate.opsForValue().get("RT:" + authentication.getName());

        if (ObjectUtils.isEmpty(refreshToken)) {
            return response.fail("Refresh Token is empty", HttpStatus.BAD_REQUEST);
        }

        if (!refreshToken.equals(reissue.getRefreshToken())) {
            return response.fail("Refresh Token is invalid", HttpStatus.BAD_REQUEST);
        }
        MemberResponseDto.TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
        redisTemplate.opsForValue().set("RT:" + authentication.getName(), tokenInfo.getRefreshToken(), tokenInfo.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

        return response.success(tokenInfo, "Refresh token is reissued", HttpStatus.CREATED);
    }

    public ResponseEntity<?> logout(MemberRequestDto.Logout logout) {
        if (!jwtTokenProvider.validateToken(logout.getAccessToken())) {
            return response.fail("Refresh Token is invalid", HttpStatus.BAD_REQUEST);
        }

        Authentication authentication = jwtTokenProvider.getAuthentication(logout.getAccessToken());

        if (redisTemplate.opsForValue().get("RT:" + authentication.getName()) != null) {
            redisTemplate.delete("RT:" + authentication.getName());
        }

        Long expiration = jwtTokenProvider.getExpiration(logout.getAccessToken());
        redisTemplate.opsForValue().set(logout.getAccessToken(), "logout", expiration, TimeUnit.MILLISECONDS);
        return response.success("logout successfully", HttpStatus.NO_CONTENT);
    }

    public Member findMemberByToken() {
        return Member.builder().build();
    }

    @Override
    public boolean existsEmail(String email) {
        return memberRepository.existsMemberByEmailEquals(email);
    }

    @Override
    public boolean existsUsername(String username) {
        return memberRepository.existsMemberByUsernameEquals(username);
    }


}
