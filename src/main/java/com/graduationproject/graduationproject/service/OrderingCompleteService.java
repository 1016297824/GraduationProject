package com.graduationproject.graduationproject.service;

import com.graduationproject.graduationproject.entity.OrderingComplete;
import com.graduationproject.graduationproject.repository.OrderingCompleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class OrderingCompleteService {

    @Autowired
    private OrderingCompleteRepository orderingCompleteRepository;

    // 保存结算信息
    public void save(OrderingComplete orderingComplete) {

        orderingCompleteRepository.save(orderingComplete);
    }

    // 通过时间获得结算信息
    public List<OrderingComplete> findByTime(LocalDateTime startTime, LocalDateTime endTime) {

        return orderingCompleteRepository.findByTime(startTime, endTime);
    }
}
