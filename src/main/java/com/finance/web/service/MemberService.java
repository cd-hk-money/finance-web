package com.finance.web.service;

import com.finance.web.dto.MemberDto;
import com.finance.web.dto.MemberSaveRequestDto;
import com.finance.web.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    public MemberDto join(MemberSaveRequestDto memberSaveRequestDto);

    public boolean validateDuplicateMember(MemberSaveRequestDto memberSaveRequestDto);

    public List<Member> findMembers();

    public Optional<Member> findOneByUsername(String username);

    public Optional<Member> findOne(Long memberId);

}
