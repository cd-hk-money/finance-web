package com.finance.web.controller;

import com.finance.web.dto.MemberDto;
import com.finance.web.dto.MemberSaveRequestDto;
import com.finance.web.dto.RequestLogin;
import com.finance.web.service.MemberService;
import com.finance.web.service.MemberServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberServiceImpl memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/sign-up")
    public MemberDto signUp(@RequestBody MemberSaveRequestDto memberSaveRequestDto) {
        return memberService.join(memberSaveRequestDto);
    }

    @GetMapping("/login")
    public String login(@RequestBody RequestLogin requestLogin) {
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

}
