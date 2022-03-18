package com.finance.web.repository;

import com.finance.web.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InterestRepository extends JpaRepository<Interest, Long> {

    @Query("select i from Interest i where i.id = :id")
    Optional<Interest> findById(@Param("id") Long id);

    @Query("select i from Interest i where i.stockCode = :stockCode")
    List<Interest> findAllByStockCode(@Param("stockCode") String stockCode);

    @Query("select i from Interest i where i.interestListId = :interestListId")
    List<Interest> findAllByInterestListId(@Param("interestListId") Long interestListId);

    @Query("select i from Interest i where i.interestListId = :interestListId and i.notification = true")
    List<Interest> findAllByInterestListIdAndNotificationTrue(@Param("interestListId") Long interestListId);

}
