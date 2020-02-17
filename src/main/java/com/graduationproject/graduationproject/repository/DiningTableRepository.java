package com.graduationproject.graduationproject.repository;

import com.graduationproject.graduationproject.entity.DiningTable;
import com.graduationproject.graduationproject.repository.impl.CustomizedRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.From;
import java.util.List;

@Repository
public interface DiningTableRepository extends CustomizedRepository<DiningTable, Integer> {

    @Query("from DiningTable dt")
    List<DiningTable> findAll();

    @Query("select dt from DiningTable dt where dt.id=:id")
    DiningTable findById(@Param("id") int id);
}
