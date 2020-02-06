package com.graduationproject.graduationproject.repository;

import com.graduationproject.graduationproject.entity.Customer;
import com.graduationproject.graduationproject.repository.impl.CustomizedRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CustomizedRepository<Customer, Integer> {

    @Query("from Customer cus")
    List<Customer> findAll();

    @Query("select cus from Customer cus where cus.username=:username ")
    Customer findByUsername(@Param("username") String username);
}
