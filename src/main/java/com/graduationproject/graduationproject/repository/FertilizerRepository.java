package com.graduationproject.graduationproject.repository;

import com.graduationproject.graduationproject.entity.Fertilizer;
import com.graduationproject.graduationproject.repository.impl.CustomizedRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FertilizerRepository extends CustomizedRepository<Fertilizer, Integer> {

}
