package com.finance.web.repository;

import com.finance.web.entity.Interest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class InterestRepositoryTest {

    @Autowired
    InterestRepository interestRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    void 생성() {
        //given
        Interest interest = Interest.builder()
                .interestGroupId(1L)
                .stockCode("035720")
                .sequence(1)
                .notification(true)
                .build();

        //when
        Interest savedOne = interestRepository.save(interest);
        Optional<Interest> findOne = interestRepository.findById(interest.getId());

        //then
        assertThat(findOne.get()).isEqualTo(savedOne);
        assertThat(findOne.get().getId()).isEqualTo(savedOne.getId());
        assertThat(findOne.get().getInterestGroupId()).isEqualTo(savedOne.getInterestGroupId());
        assertThat(findOne.get().getStockCode()).isEqualTo(savedOne.getStockCode());
    }

}