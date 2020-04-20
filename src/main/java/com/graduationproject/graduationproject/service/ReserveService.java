package com.graduationproject.graduationproject.service;

import com.graduationproject.graduationproject.entity.Customer;
import com.graduationproject.graduationproject.entity.DiningTable;
import com.graduationproject.graduationproject.entity.Reserve;
import com.graduationproject.graduationproject.repository.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ReserveService {

    @Autowired
    private ReserveRepository reserveRepository;

    // 通过顾客用户名获得订单信息
    public List<Reserve> findByCustomerUsername(String username) {

        return reserveRepository.findByCustomerUsername(username);
    }

    // 通过订单号获得订单信息
    public Reserve findByNo(String no) {

        return reserveRepository.findByNo(no);
    }

    // 删除指定订单信息
    public void deleteReserve(Reserve reserve) {

        reserveRepository.delete(reserve);
    }

    // 通过时间获得当时已经被预定的桌位
    public List<DiningTable> findDiningTableByTime(
            LocalDateTime startTime,
            LocalDateTime endTime) {

        return reserveRepository.findDiningTableByTime(startTime, endTime);
    }

    // 获得订单数量
    public int getCount() {

        return reserveRepository.getCount();
    }

    // 获得最大订单号
    public int getMaxNo() {
        return reserveRepository.getMaxNo();
    }

    // 存入订单信息
    public void save(Reserve reserve) {

        reserveRepository.save(reserve);
    }

    // 查询过期订单
    public List<Reserve> findAllOverdueReserve(LocalDateTime nowTime) {

        return reserveRepository.findAllOverdueReserve(nowTime);
    }

    // 查询所有订单
    public List<Reserve> findAll() {

        return reserveRepository.findAll();
    }

    // 查询所有用户订单
    public List<Reserve> findAllCustomerNotNull() {

        return reserveRepository.findAllCustomerNotNull();
    }
}
