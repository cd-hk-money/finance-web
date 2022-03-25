package com.finance.web.repository;

import com.finance.web.entity.InterestGroup;
import com.finance.web.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InterestGroupRepository extends JpaRepository<InterestGroup, Long> {

    @Query("select i from InterestGroup i where i.member = :member")
    List<InterestGroup> findAllByMember(@Param("member") Member member);
}
