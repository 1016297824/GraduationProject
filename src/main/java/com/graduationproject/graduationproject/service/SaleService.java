package com.graduationproject.graduationproject.service;

import com.graduationproject.graduationproject.entity.Sale;
import com.graduationproject.graduationproject.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    // 保存销售信息
    public void saveAll(List<Sale> saleList) {

        saleRepository.saveAll(saleList);
    }

    // 通过日期获得销售信息
    public List<Sale> findByTime(LocalDateTime startTime, LocalDateTime endTime) {

        return saleRepository.findByTime(startTime, endTime);
    }

    // 通过销售号获得销售信息
    public List<Sale> findByNo(String no) {

        return saleRepository.findByNo(no);
    }
}
