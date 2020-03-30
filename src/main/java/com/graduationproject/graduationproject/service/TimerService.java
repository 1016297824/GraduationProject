package com.graduationproject.graduationproject.service;

import com.graduationproject.graduationproject.entity.Attendance;
import com.graduationproject.graduationproject.entity.Reserve;
import com.graduationproject.graduationproject.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class TimerService {

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private AttendanceService attendanceService;

//    @Scheduled(cron = "0 1 * * * ?")
//    public void cancelReserve() {
//
//        List<Reserve> reserveList = new ArrayList<Reserve>();
//
//        reserveList = reserveService.findAllOverdueReserve(LocalDateTime.now());
//        if (!reserveList.isEmpty()) {
//            for (Reserve reserve : reserveList) {
//                reserveService.deleteReserve(reserve);
//            }
//        }
//    }

    //@Scheduled(cron = "0 * * * * ?")
    @Scheduled(cron = "0 0 0 * * ?")
    // 每日初始化考勤表
    public void createAttendance() {

        List<Staff> staffList = new ArrayList<Staff>();
        List<Attendance> attendanceList = new ArrayList<Attendance>();

        staffList = staffService.findAllWorked();
        for (Staff staff : staffList) {

            Attendance attendance = new Attendance();

            if (staff.getPosition().getAuthority().equals("SuperManager")) {
                attendance.setWorkingHours(8);
            } else {
                attendance.setWorkingHours(0);
            }
            attendance.setStaff(staff);
            attendanceList.add(attendance);
        }

        attendanceService.saveAll(attendanceList);
    }
}
