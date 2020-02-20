package com.graduationproject.graduationproject.repository;

import com.graduationproject.graduationproject.entity.Menu;
import com.graduationproject.graduationproject.repository.impl.CustomizedRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends CustomizedRepository<Menu, Integer> {

    @Query("from Menu m")
    List<Menu> findAll();
}
