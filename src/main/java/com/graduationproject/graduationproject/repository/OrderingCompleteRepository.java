package com.graduationproject.graduationproject.repository;

import com.graduationproject.graduationproject.entity.OrderingComplete;
import com.graduationproject.graduationproject.repository.impl.CustomizedRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderingCompleteRepository extends CustomizedRepository<OrderingComplete, Integer> {

    @Query("select oc from OrderingComplete oc " +
            "where oc.insertTime>=:startTime " +
            "and oc.insertTime<:endTime")
    List<OrderingComplete> findByTime(@Param("startTime") LocalDateTime startTime,
                                      @Param("endTime") LocalDateTime endTime);
}
