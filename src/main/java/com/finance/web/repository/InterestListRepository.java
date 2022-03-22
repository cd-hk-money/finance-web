package com.finance.web.repository;

import com.finance.web.entity.InterestList;
import com.finance.web.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface InterestListRepository extends JpaRepository<InterestList, Long> {

    @Query("select i from InterestList i where i.member = :member")
    List<InterestList> findAllByMember(@Param("member") Member member);
}
