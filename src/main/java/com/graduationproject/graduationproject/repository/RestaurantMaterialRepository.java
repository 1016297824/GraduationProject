package com.graduationproject.graduationproject.repository;

import com.graduationproject.graduationproject.entity.RestaurantMaterial;
import com.graduationproject.graduationproject.repository.impl.CustomizedRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantMaterialRepository extends CustomizedRepository<RestaurantMaterial, Integer> {

    @Query("from RestaurantMaterial ")
    List<RestaurantMaterial> findAll();

    @Query("select rm from RestaurantMaterial rm where rm.name=:name")
    RestaurantMaterial findByName(@Param("name") String name);
}
