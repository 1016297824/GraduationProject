package com.graduationproject.graduationproject.repository;

import com.graduationproject.graduationproject.entity.DiningTable;
import com.graduationproject.graduationproject.entity.Reserve;
import com.graduationproject.graduationproject.repository.impl.CustomizedRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReserveRepository extends CustomizedRepository<Reserve, Integer> {

    @Query("select re.diningTable from Reserve re " +
            "where re.endTime>:startTime " +
            "or  re.startTime<:endTime")
    List<DiningTable> findDiningTableByNow(@Param("startTime") LocalDateTime startTime,@Param("endTime") LocalDateTime endTime);
}
