package com.graduationproject.graduationproject.repository;

import com.graduationproject.graduationproject.entity.Consumption;
import com.graduationproject.graduationproject.repository.impl.CustomizedRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumptionRepository extends CustomizedRepository<Consumption,Integer> {


}
