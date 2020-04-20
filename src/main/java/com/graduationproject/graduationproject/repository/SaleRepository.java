package com.graduationproject.graduationproject.repository;

import com.graduationproject.graduationproject.entity.Sale;
import com.graduationproject.graduationproject.repository.impl.CustomizedRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends CustomizedRepository<Sale, Integer> {


}
