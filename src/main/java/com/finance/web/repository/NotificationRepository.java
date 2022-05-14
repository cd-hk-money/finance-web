package com.finance.web.repository;

import com.finance.web.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("select n from Notification n where n.stockCode = :stockCode")
    List<Notification> findAllByStockCode(@Param("stockCode") String stockCode);

    @Query("select n from Notification n where n.stockCode = :stockCode and n.createdDate = :createdDate")
    List<Notification> findAllByStockCodeAndCreatedDate(@Param("stockCode") String stockCode, LocalDateTime createdDate);
}