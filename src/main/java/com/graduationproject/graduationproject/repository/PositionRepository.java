package com.graduationproject.graduationproject.repository;

import com.graduationproject.graduationproject.entity.Position;
import com.graduationproject.graduationproject.repository.impl.CustomizedRepository;
import org.hibernate.sql.Insert;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends CustomizedRepository<Position, Integer> {

    @Query("from Position p")
    List<Position> findAll();

    @Query("select p from Position p where p.id=:id")
    Position findById(@Param("id") int id);

    @Query("select p from Position p where p.authority <> 'SuperManager'")
    List<Position> findManagerAndStaff();

    @Query("select p from Position p where p.authority='FarmStaff'")
    List<Position> findAllFarmStaff();

    @Query("select p from Position p where p.authority='RestaurantStaff'")
    List<Position> findAllRestaurantStaff();
}
