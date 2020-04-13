package com.graduationproject.graduationproject.service;

import com.graduationproject.graduationproject.entity.Purchase;
import com.graduationproject.graduationproject.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    // 保存采购信息
    public void save(Purchase purchase) {

        purchaseRepository.save(purchase);
    }
}
