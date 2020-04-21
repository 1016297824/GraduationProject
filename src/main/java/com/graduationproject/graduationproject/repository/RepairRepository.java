package com.graduationproject.graduationproject.repository;

import com.graduationproject.graduationproject.entity.Repair;
import com.graduationproject.graduationproject.repository.impl.CustomizedRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RepairRepository extends CustomizedRepository<Repair, Integer> {

    @Query("select r from Repair r where r.state=:repairState")
    List<Repair> findByState(@Param("repairState") String repairState);

    @Query("select r from Repair r " +
            "where r.insertTime>=:startTime " +
            "and r.insertTime<:endTime")
    List<Repair> findByTime(@Param("startTime") LocalDateTime startTIme,
                            @Param("endTime") LocalDateTime endTime);
}
