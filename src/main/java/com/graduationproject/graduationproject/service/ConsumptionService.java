package com.graduationproject.graduationproject.service;

import com.graduationproject.graduationproject.entity.Consumption;
import com.graduationproject.graduationproject.repository.ConsumptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ConsumptionService {

    @Autowired
    private ConsumptionRepository consumptionRepository;

    // 保存消耗信息
    public void save(Consumption consumption) {

        consumptionRepository.save(consumption);
    }
}
