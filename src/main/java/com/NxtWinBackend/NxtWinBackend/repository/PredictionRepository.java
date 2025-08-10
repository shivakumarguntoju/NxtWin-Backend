package com.NxtWinBackend.NxtWinBackend.repository;

import com.NxtWinBackend.NxtWinBackend.entity.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PredictionRepository extends JpaRepository<Prediction, Long> {
    List<Prediction> findByUserId(Long userId);
    List<Prediction> findByEventId(Long eventId);



    Integer countDistinctTradersByEventId(@Param("eventId") Long eventId);
}