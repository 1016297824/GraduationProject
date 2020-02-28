package com.graduationproject.graduationproject.service;

import com.graduationproject.graduationproject.entity.Ordering;
import com.graduationproject.graduationproject.repository.OrderingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderingService {

    @Autowired
    private OrderingRepository orderingRepository;

    // 通过订单号获得点餐信息
    public List<Ordering> findByReserveNo(String no) {

        return orderingRepository.findByReserveNo(no);
    }

    // 添加点餐信息
    public void saveAll(List<Ordering> orderingList) {

        orderingRepository.saveAll(orderingList);
    }
}
