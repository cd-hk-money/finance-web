package com.finance.web.service;

import com.finance.web.entity.InterestGroup;
import com.finance.web.entity.Member;
import com.finance.web.repository.InterestGroupRepository;
import com.finance.web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InterestGroupServiceImpl implements InterestGroupService {

    private final InterestGroupRepository interestGroupRepository;
    private final MemberRepository memberRepository;

    public Long create(Long memberId, String groupName, Integer sequence) {
        Optional<Member> id = memberRepository.findOneById(memberId);
        if (id.isPresent()) {
            Member member = id.get();
            InterestGroup interestGroup = InterestGroup.builder()
                    .name(groupName)
                    .sequence(sequence)
                    .member(member)
                    .build();
            InterestGroup save = interestGroupRepository.save(interestGroup);
            return save.getId();
        }
        return null;
    }

    public void delete(Long groupId) {
        Optional<InterestGroup> byId = interestGroupRepository.findById(groupId);
        if (byId.isPresent()) {
            interestGroupRepository.delete(byId.get());
        }
    }


    public void updateName(Long interestGroupId, String name) {
        Optional<InterestGroup> byId = interestGroupRepository.findById(interestGroupId);
        if (byId.isPresent()) {
            interestGroupRepository.updateName(interestGroupId, name);
        }
    }

    public void changeSequence(Integer sequence) {

    }
}
