package com.graduationproject.graduationproject.repository;

import com.graduationproject.graduationproject.entity.SaleNo;
import com.graduationproject.graduationproject.repository.impl.CustomizedRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleNoRepository extends CustomizedRepository<SaleNo, Integer> {

    @Query("select count(s) from SaleNo s")
    int getCount();

    @Query("select max(s.no) from SaleNo s")
    int getMaxNO();

    @Query("select s from SaleNo s where s.no=:no")
    SaleNo findByNo(@Param("no") String no);
}
