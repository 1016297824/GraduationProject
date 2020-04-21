package com.graduationproject.graduationproject.repository;

import com.graduationproject.graduationproject.entity.Sale;
import com.graduationproject.graduationproject.repository.impl.CustomizedRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SaleRepository extends CustomizedRepository<Sale, Integer> {

    @Query("select s from Sale s " +
            "where s.saleNo.insertTime>=:startTime " +
            "and s.saleNo.insertTime<:endTime")
    List<Sale> findByTime(@Param("startTime") LocalDateTime startTime,
                         @Param("endTime") LocalDateTime endTime);

    @Query("select s from  Sale s where s.saleNo.no=:no")
    List<Sale> findByNo(@Param("no") String no);
}
