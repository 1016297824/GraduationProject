package com.graduationproject.graduationproject.entity.body;

import com.graduationproject.graduationproject.entity.Staff;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
// 考勤Excel
public class AttendanceExcel {

    // 工时
    private double workingHours;

    // 总工时
    private double totalWorkingHours;

    // 实付工资
    private double trueSalary;

    private Staff staff;

    public AttendanceExcel(double workingHours, double totalWorkingHours, Staff staff) {

        this.workingHours = workingHours;
        this.totalWorkingHours = totalWorkingHours;
        this.staff = staff;
    }
}
