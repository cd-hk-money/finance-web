package com.finance.web.service;

import com.finance.web.dto.MemberSaveRequestDto;
import com.finance.web.entity.Member;
import com.finance.web.repository.MemberRepository;
import com.finance.web.service.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    @Transactional
    public Long join(MemberSaveRequestDto memberSaveRequestDto) {
        validateDuplicateMember(memberSaveRequestDto);
        Member save = memberRepository.save(memberSaveRequestDto.toEntity());
        return save.getId();
    }

    @Override
    public Optional<Member> findOneByUsername(String username) {
        Optional<Member> findMember = memberRepository.findOneByUsername("username");
        return findMember;
    }


    @Override
    public boolean validateDuplicateMember(MemberSaveRequestDto memberSaveRequestDto) {
        List<Member> findMembers = memberRepository.findListByEmail(memberSaveRequestDto.getEmail());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
        return true;
    }

    // 회원 전체 조회
    @Override
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findOneById(memberId);
    }

}
