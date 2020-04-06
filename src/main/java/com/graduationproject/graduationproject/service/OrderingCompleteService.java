package com.graduationproject.graduationproject.service;

import com.graduationproject.graduationproject.entity.OrderingComplete;
import com.graduationproject.graduationproject.repository.OrderingCompleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderingCompleteService {

    @Autowired
    private OrderingCompleteRepository orderingCompleteRepository;

    // 保存结算信息
    public void save(OrderingComplete orderingComplete) {

        orderingCompleteRepository.save(orderingComplete);
    }
}
