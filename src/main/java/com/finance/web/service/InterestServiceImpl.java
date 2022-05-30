package com.finance.web.service;

import com.finance.web.dto.InterestGroupDto;
import com.finance.web.dto.InterestEnrollRequestDto;
import com.finance.web.dto.InterestResponseDto;
import com.finance.web.dto.InterestUnenrollRequestDto;
import com.finance.web.entity.Interest;
import com.finance.web.repository.InterestGroupRepository;
import com.finance.web.repository.InterestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InterestServiceImpl implements InterestService {

    private final InterestRepository interestRepository;
    private final InterestGroupRepository interestGroupRepository;


    public InterestResponseDto enroll(InterestEnrollRequestDto interestEnrollRequestDto) {
        Interest save = interestRepository.save(interestEnrollRequestDto.toEntity());
        return save.toResponseDto();
    }


    /**
     * HashSet과 List중 반환 타입에 대한 고민
     * 결국 HashSet을 선택.
     * https://stackoverflow.com/questions/150750/hashset-vs-list-performance
     * 성능 향상 기대.
     * 그리고 중복 허용치 않는 set 로 n+1 문제에 대응
     *
     * @param interestGroupDto
     * @return
     */
    @Override
    public HashSet<Interest> getInterests(InterestGroupDto interestGroupDto) {
        return interestRepository.findAllByInterestGroupId(interestGroupDto.getId());
    }

    @Override
    public void unEnroll(InterestUnenrollRequestDto requestDto) {
        Optional<Interest> optionalInterest = interestRepository.findById(requestDto.getInterestId());
        if (optionalInterest.isPresent()) {
            Interest interest = optionalInterest.get();
            interestRepository.delete(interest);
        }
    }
}
