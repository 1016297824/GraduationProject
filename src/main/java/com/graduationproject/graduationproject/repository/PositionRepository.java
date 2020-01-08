package com.graduationproject.graduationproject.repository;

import com.graduationproject.graduationproject.entity.Position;
import com.graduationproject.graduationproject.repository.impl.CustomizedRepository;
import org.hibernate.sql.Insert;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends CustomizedRepository<Position,Integer> {

    @Query("from Position p")
    List<Position> findAll();
}
