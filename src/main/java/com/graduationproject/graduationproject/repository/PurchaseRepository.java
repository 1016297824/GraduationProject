package com.graduationproject.graduationproject.repository;

import com.graduationproject.graduationproject.entity.Product;
import com.graduationproject.graduationproject.entity.Purchase;
import com.graduationproject.graduationproject.repository.impl.CustomizedRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PurchaseRepository extends CustomizedRepository<Purchase, Integer> {

    @Query("select p from Purchase p " +
            "where p.insertTime>=:startTime " +
            "and p.insertTime<:endTime")
    List<Purchase> findByTime(@Param("startTime") LocalDateTime startTime,
                              @Param("endTime") LocalDateTime endTime);
}
