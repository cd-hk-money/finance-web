package com.finance.web.service;

import com.finance.web.entity.Member;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    public Long join(Member member);

    public List<Member> findMembers();

    public Optional<Member> findOne(Long memberId);

}
