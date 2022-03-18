package com.finance.web.repository;

import com.finance.web.entity.InterestList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface InterestListRepository extends JpaRepository<InterestList, Long> {
}
