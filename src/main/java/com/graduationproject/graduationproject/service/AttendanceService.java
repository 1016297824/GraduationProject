package com.graduationproject.graduationproject.service;

import com.graduationproject.graduationproject.entity.Attendance;
import com.graduationproject.graduationproject.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    // 保存所有出勤信息
    public void saveAll(List<Attendance> attendanceList) {

        attendanceRepository.saveAll(attendanceList);
    }
}
