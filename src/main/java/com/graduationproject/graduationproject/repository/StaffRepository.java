package com.graduationproject.graduationproject.repository;

import com.graduationproject.graduationproject.entity.Staff;
import com.graduationproject.graduationproject.repository.impl.CustomizedRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends CustomizedRepository<Staff, Integer> {

    @Query("from Staff s")
    List<Staff> findAll();

    @Query("select s from Staff s where s.username=:username")
    Staff findByUsername(@Param("username") String username);
}
