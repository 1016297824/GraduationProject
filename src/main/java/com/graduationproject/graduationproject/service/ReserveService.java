package com.graduationproject.graduationproject.service;

import com.graduationproject.graduationproject.entity.DiningTable;
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

    public List<DiningTable> findDiningTableByTime(LocalDateTime startTime,LocalDateTime endTime){
        return reserveRepository.findDiningTableByTime(startTime, endTime);
    }
}
