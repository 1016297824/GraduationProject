package com.graduationproject.graduationproject.repository;

import com.graduationproject.graduationproject.entity.Product;
import com.graduationproject.graduationproject.entity.Purchase;
import com.graduationproject.graduationproject.repository.impl.CustomizedRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends CustomizedRepository<Purchase, Integer> {


}
