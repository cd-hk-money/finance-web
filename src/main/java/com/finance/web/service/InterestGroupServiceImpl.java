package com.finance.web.service;

import com.finance.web.dto.InterestGroupDto;
import com.finance.web.dto.InterestGroupResponseDto;
import com.finance.web.dto.InterestResponseDto;
import com.finance.web.entity.Interest;
import com.finance.web.entity.InterestGroup;
import com.finance.web.entity.Member;
import com.finance.web.repository.InterestGroupRepository;
import com.finance.web.repository.MemberRepository;
import com.finance.web.service.mapper.InterestGroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InterestGroupServiceImpl implements InterestGroupService {

    private final InterestGroupRepository interestGroupRepository;
    private final InterestService interestService;
    private final MemberRepository memberRepository;
    private final InterestGroupMapper interestGroupMapper;

    public InterestGroupDto create(Long memberId, InterestGroupDto interestGroupDto) {
        Optional<Member> optionalMember = memberRepository.findOneById(memberId);
        if (optionalMember.isPresent()) {
            InterestGroup interestGroup = interestGroupMapper.toEntity(interestGroupDto);
            InterestGroup save = interestGroupRepository.save(interestGroup);
            return interestGroupMapper.toDto(save);
        }
        return null;
    }

    @Override
    public List<InterestGroupResponseDto> getInterestGroups(Long memberId) {
        List<InterestGroupResponseDto> responseDtos = new ArrayList<>();
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            List<InterestGroup> interestGroups = interestGroupRepository.findAllByMember(member);

            for (InterestGroup interestGroup : interestGroups) {
                Long id = interestGroup.getId();
                String name = interestGroup.getName();
                HashSet<InterestResponseDto> item = (HashSet<InterestResponseDto>) interestService.getInterests(interestGroupMapper.toDto(interestGroup)).stream()
                        .map(Interest::toResponseDto)
                        .collect(Collectors.toSet());
                responseDtos.add(new InterestGroupResponseDto(id, name, item));
            }
            return responseDtos;
        }
        return null;
    }

    public void delete(Long groupId) {
        Optional<InterestGroup> optionalInterestGroup = interestGroupRepository.findById(groupId);
        if (optionalInterestGroup.isPresent()) {
            InterestGroup interestGroup = optionalInterestGroup.get();
            interestGroupRepository.delete(interestGroup);
        }
    }

    public void updateName(Long interestGroupId, String name) {
        Optional<InterestGroup> optionalInterestGroup = interestGroupRepository.findById(interestGroupId);
        if (optionalInterestGroup.isPresent()) {
            interestGroupRepository.updateName(interestGroupId, name);
        }
    }

    public void changeSequence(Integer sequence) {

    }
}
