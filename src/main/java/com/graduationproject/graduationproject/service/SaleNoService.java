package com.graduationproject.graduationproject.service;

import com.graduationproject.graduationproject.entity.Sale;
import com.graduationproject.graduationproject.entity.SaleNo;
import com.graduationproject.graduationproject.repository.SaleNoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SaleNoService {

    @Autowired
    private SaleNoRepository saleNoRepository;

    // 获得订单数量
    public int getCount() {

        return saleNoRepository.getCount();
    }

    // 获得最大销售号
    public int getMaxNO() {

        return saleNoRepository.getMaxNO();
    }

    // 删除销售号信息
    public void deleteSaleNo(SaleNo saleNo) {

        saleNoRepository.delete(saleNo);
    }

    // 通过销售号查询销售号信息
    public SaleNo findByNo(String no) {

        return saleNoRepository.findByNo(no);
    }

    // 保存销售号信息
    public void save(SaleNo saleNo) {

        saleNoRepository.save(saleNo);
    }
}
