package com.graduationproject.graduationproject.service;

import com.graduationproject.graduationproject.entity.Sale;
import com.graduationproject.graduationproject.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
}
