package com.graduationproject.graduationproject.repository;

import com.graduationproject.graduationproject.entity.Repair;
import com.graduationproject.graduationproject.repository.impl.CustomizedRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairRepository extends CustomizedRepository<Repair,Integer> {

}
