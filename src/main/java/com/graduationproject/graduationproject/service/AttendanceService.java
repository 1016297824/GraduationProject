package com.graduationproject.graduationproject.service;

import com.graduationproject.graduationproject.entity.Attendance;
import com.graduationproject.graduationproject.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    // 查询所有出勤信息
    public List<Attendance> findAll() {

        return attendanceRepository.findAll();
    }

    // 保存所有出勤信息
    public void saveAll(List<Attendance> attendanceList) {

        attendanceRepository.saveAll(attendanceList);
    }

    // 查询所有管理员出勤信息
    public List<Attendance> findManagerByChooseTime(LocalDateTime chooseDate1, LocalDateTime chooseDate2) {

        return attendanceRepository.findManagerByChooseTime(chooseDate1, chooseDate2);
    }

    // 查询所有农场员工出勤信息
    public List<Attendance> findFarmStaffByChooseTime(LocalDateTime chooseDate1, LocalDateTime chooseDate2) {

        return attendanceRepository.findFarmStaffByChooseTime(chooseDate1, chooseDate2);
    }

    // 查询所有餐厅员工出勤信息
    public List<Attendance> findRestaurantStaffByChooseTime(LocalDateTime chooseDate1, LocalDateTime chooseDate2) {

        return attendanceRepository.findRestaurantStaffByChooseTime(chooseDate1, chooseDate2);
    }
}
