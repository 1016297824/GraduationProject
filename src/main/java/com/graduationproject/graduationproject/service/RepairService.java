package com.graduationproject.graduationproject.service;

import com.graduationproject.graduationproject.entity.Repair;
import com.graduationproject.graduationproject.repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class RepairService {

    @Autowired
    private RepairRepository repairRepository;

    // 保存报修报损信息
    public void save(Repair repair) {

        repairRepository.save(repair);
    }

    // 根据状态查询所有报修报损信息
    public List<Repair> findByState(String repairState) {

        return repairRepository.findByState(repairState);
    }

    // 删除报修报损信息
    public void deleteRepair(Repair repair) {

        repairRepository.delete(repair);
    }

    // 通过时间获得报修报损信息
    public List<Repair> findByTime(LocalDateTime startTime, LocalDateTime endTime) {

        return repairRepository.findByTime(startTime, endTime);
    }
}
