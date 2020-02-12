package com.graduationproject.graduationproject.repository;

import com.graduationproject.graduationproject.entity.DiningTable;
import com.graduationproject.graduationproject.entity.Reserve;
import com.graduationproject.graduationproject.repository.impl.CustomizedRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReserveRepository extends CustomizedRepository<Reserve, Integer> {

    @Query("select re.diningTable from Reserve re " +
            "where (re.endTime>:startTime and re.startTime<:startTime)" +
            "or (re.endTime>:endTime and re.startTime<:endTime)")
    List<DiningTable> findDiningTableByTime(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}
