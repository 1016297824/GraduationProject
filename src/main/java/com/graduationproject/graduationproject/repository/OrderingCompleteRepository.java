package com.graduationproject.graduationproject.repository;

import com.graduationproject.graduationproject.entity.OrderingComplete;
import com.graduationproject.graduationproject.repository.impl.CustomizedRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderingCompleteRepository  extends CustomizedRepository<OrderingComplete, Integer> {

}
