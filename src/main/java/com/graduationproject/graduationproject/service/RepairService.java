package com.graduationproject.graduationproject.service;

import com.graduationproject.graduationproject.entity.Repair;
import com.graduationproject.graduationproject.repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RepairService {

    @Autowired
    private RepairRepository repairRepository;

    //
    public void save(Repair repair) {

        repairRepository.save(repair);
    }
}
