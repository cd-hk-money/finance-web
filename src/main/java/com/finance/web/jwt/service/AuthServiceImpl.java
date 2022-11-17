package com.finance.web.jwt.service;

import com.finance.web.domain.Member;
import com.finance.web.dto.MemberDto;
import com.finance.web.dto.MemberRequestDto;
import com.finance.web.dto.MemberResponseDto;
import com.finance.web.exception.NotExistUserException;
import com.finance.web.jwt.utils.JwtTokenProvider;
import com.finance.web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate redisTemplate;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Override
    @Transactional
    public MemberDto signUpMember(MemberRequestDto.SignUp request) {
        if (isAvailableEmail(request.getEmail()) && isAvailableUsername(request.getUsername())) {
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

            return memberRepository.save(member).toDto();
        }

        throw new IllegalArgumentException("회원 등록에 실패했어요 :(");
    }

    @Override
    public MemberResponseDto.TokenInfo login(MemberRequestDto.Login login) {
        Member member = memberRepository.findMemberByUsername(login.getUsername()).orElseThrow(()
                -> new NotExistUserException("존재하지 않는 회원이에요!"));

        UsernamePasswordAuthenticationToken authenticationToken = login.toAuthentication();
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        MemberResponseDto.TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(), tokenInfo.getRefreshToken(), tokenInfo.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

        return tokenInfo;
    }


    public Member findMemberByToken() {
        return Member.builder().build();
    }

    @Override
    public boolean isAvailableEmail(String email) {
        return !memberRepository.existsMemberByEmailEquals(email);
    }

    @Override
    public boolean isAvailableUsername(String username) {
        return !memberRepository.existsMemberByUsernameEquals(username);
    }


}
