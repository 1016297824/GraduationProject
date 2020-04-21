package com.graduationproject.graduationproject.controller;

import com.graduationproject.graduationproject.component.ExcelComponent;
import com.graduationproject.graduationproject.entity.*;
import com.graduationproject.graduationproject.entity.body.AttendanceExcel;
import com.graduationproject.graduationproject.entity.body.FarmSaleExcel;
import com.graduationproject.graduationproject.entity.body.PageBody1;
import com.graduationproject.graduationproject.entity.body.UserBody1;
import com.graduationproject.graduationproject.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/farmManager")
// 农场管理员功能
public class FarmManagerController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StaffService staffService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private SaleService saleService;

    @Autowired
    private SaleNoService saleNoService;

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/getStaff")
    // 获得员工信息
    public Map getStaff() {
        //System.out.println("get success!");

        int page = 1;
        int pages = 0;
        List<Integer> pageList = new ArrayList<Integer>();
        PageBody1 pageBody1 = new PageBody1();
        List<Staff> staffList = new ArrayList<Staff>();
        List<Staff> staffList1 = new ArrayList<Staff>();

        staffList = staffService.findAllFarmStaff();
        if (!staffService.findByPositionAuthorityIsNull().isEmpty()) {
            staffList.addAll(staffService.findByPositionAuthorityIsNull());
        }

        if (staffList.size() / 5 >= 5) {
            for (int i = 0; i < 5; i++) {
                pageList.add(i + 1);
            }

            if (staffList.size() % 5 != 0) {
                pages = (staffList.size() + 5) / 5;
            } else {
                pages = staffList.size() / 5;
            }
            staffList1 = staffList.subList(0, 5);
        } else {
            if (!staffList.isEmpty()) {
                if (staffList.size() % 5 != 0) {
                    pages = (staffList.size() + 5) / 5;
                } else {
                    pages = staffList.size() / 5;
                }

                for (int i = 0; i < pages; i++) {
                    pageList.add(i + 1);
                }

                if (staffList.size() < 5) {
                    staffList1 = staffList.subList(0, staffList.size());
                } else {
                    staffList1 = staffList.subList(0, 5);
                }
            }
        }

        pageBody1.setPage(page);
        pageBody1.setPages(pages);
        pageBody1.setPageList(pageList);

        List<Position> positionList = positionService.findAllFarmStaff();

        return Map.of("staffList", staffList,
                "staffList1", staffList1,
                "pageBody1", pageBody1,
                "positionList", positionList);
    }

    @PostMapping("/addStaff")
    // 添加员工
    public Map addStaff(@RequestBody Staff staff) {
        //System.out.println("post success!" + staff.getName());

        int count = staffService.getCount();
        if (count == 0) {
            staff.setUsername("10001");
        } else {
            int username = staffService.getMaxNo() + 1;
            staff.setUsername(String.valueOf(username));
        }
        staff.setPassword(passwordEncoder.encode("111111"));
        staffService.save(staff);

        return Map.of("message", "添加成功！");
    }

    @PostMapping("/deleteStaff/{username}")
    // 解雇员工
    public Map deleteStaff(@PathVariable String username) {
        //System.out.println("post success!" + username);

        Staff staff = staffService.findByUsername(username);
        staffService.deleteStaff(staff);

        return Map.of("message", "已解雇！");
    }

    @PostMapping("takeOffice/{id}")
    // 任职或调任员工
    public Map takeOffice(@RequestBody Staff staff, @PathVariable int id) {
        //System.out.println("post success!" + staff.getUsername() + id);

        Staff staff1 = new Staff();
        Position position = positionService.findById(id);
        staff1 = staffService.findByUsername(staff.getUsername());

        staff1.setPosition(position);
        staffService.save(staff1);

        return Map.of("message", "任职成功！");
    }

    @PostMapping("changePassword")
    // 修改密码
    public Map changePassword(@RequestBody UserBody1 userBody1) {
        //System.out.println("post success!" + userBody1.getUsername());

        String message = "";
        Staff staff = new Staff();

        staff = staffService.findByUsername(userBody1.getUsername());
        if (!passwordEncoder.matches(userBody1.getPassword(), staff.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "旧密码错误！");
        } else {
            staff.setPassword(passwordEncoder.encode(userBody1.getNewPassword()));
            staffService.updateStaff(staff);
            message = "修改成功！";
        }

        return Map.of("message", message);
    }

    @GetMapping("initAttendance")
    // 初始化考勤信息
    public Map initAttendance() {
        //System.out.println("get success!");

        int page = 1;
        int pages = 0;
        List<Integer> pageList = new ArrayList<Integer>();
        PageBody1 pageBody1 = new PageBody1();
        List<Attendance> attendanceList = new ArrayList<Attendance>();
        List<Attendance> attendanceList1 = new ArrayList<Attendance>();

        LocalDateTime choosedDate = LocalDateTime.now();
        LocalDateTime choosedDate1 = LocalDateTime.now().withHour(0);
        LocalDateTime choosedDate2 = LocalDateTime.now().withHour(23);
        attendanceList = attendanceService.findFarmStaffByChooseTime(choosedDate1, choosedDate2);

        if (attendanceList.size() / 5 >= 5) {
            for (int i = 0; i < 5; i++) {
                pageList.add(i + 1);
            }

            if (attendanceList.size() % 5 != 0) {
                pages = (attendanceList.size() + 5) / 5;
            } else {
                pages = attendanceList.size() / 5;
            }
            attendanceList1 = attendanceList.subList(0, 5);
        } else {
            if (!attendanceList.isEmpty()) {
                if (attendanceList.size() % 5 != 0) {
                    pages = (attendanceList.size() + 5) / 5;
                } else {
                    pages = attendanceList.size() / 5;
                }

                for (int i = 0; i < pages; i++) {
                    pageList.add(i + 1);
                }

                if (attendanceList.size() < 5) {
                    attendanceList1 = attendanceList.subList(0, attendanceList.size());
                } else {
                    attendanceList1 = attendanceList.subList(0, 5);
                }
            }
        }

        pageBody1.setPage(page);
        pageBody1.setPages(pages);
        pageBody1.setPageList(pageList);

        return Map.of("attendanceList", attendanceList,
                "attendanceList1", attendanceList1,
                "pageBody1", pageBody1);
    }

    @PostMapping("chooseDate")
    // 选择时间，获得考勤信息
    public Map chooseDate(@RequestBody LocalDateTime choosedDate) {
        //System.out.println("post success!" + chooseTime + chooseTime.plusHours(8));

        int page = 1;
        int pages = 0;
        List<Integer> pageList = new ArrayList<Integer>();
        PageBody1 pageBody1 = new PageBody1();
        List<Attendance> attendanceList = new ArrayList<Attendance>();
        List<Attendance> attendanceList1 = new ArrayList<Attendance>();

        choosedDate = choosedDate.plusHours(8);
        LocalDateTime choosedDate1 = choosedDate.withHour(0);
        LocalDateTime choosedDate2 = choosedDate.withHour(23);
        attendanceList = attendanceService.findFarmStaffByChooseTime(choosedDate1, choosedDate2);

        if (attendanceList.size() / 5 >= 5) {
            for (int i = 0; i < 5; i++) {
                pageList.add(i + 1);
            }

            if (attendanceList.size() % 5 != 0) {
                pages = (attendanceList.size() + 5) / 5;
            } else {
                pages = attendanceList.size() / 5;
            }
            attendanceList1 = attendanceList.subList(0, 5);
        } else {
            if (!attendanceList.isEmpty()) {
                if (attendanceList.size() % 5 != 0) {
                    pages = (attendanceList.size() + 5) / 5;
                } else {
                    pages = attendanceList.size() / 5;
                }

                for (int i = 0; i < pages; i++) {
                    pageList.add(i + 1);
                }

                if (attendanceList.size() < 5) {
                    attendanceList1 = attendanceList.subList(0, attendanceList.size());
                } else {
                    attendanceList1 = attendanceList.subList(0, 5);
                }
            }
        }

        pageBody1.setPage(page);
        pageBody1.setPages(pages);
        pageBody1.setPageList(pageList);

        return Map.of("attendanceList", attendanceList,
                "attendanceList1", attendanceList1,
                "pageBody1", pageBody1);
    }

    @PostMapping("updateAttendance")
    // 更新考勤信息
    public Map updateAttendance(@RequestBody List<Attendance> attendanceList) {
        //System.out.println("post success!" + attendanceList.size());

        attendanceService.saveAll(attendanceList);

        return Map.of("message", "已提交！");
    }

    @PostMapping("downloadAttendanceExcel")
    // 导出考勤表
    public void downloadAttendanceExcel(@RequestBody LocalDateTime choosedDate, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //System.out.println("get success!" + choosedDate);

        Map map = new HashMap();
        int days = 0;
        String outFileName = "";
        List<Staff> staffList = new ArrayList<Staff>();
        List<Attendance> attendanceList = new ArrayList<Attendance>();
        List<AttendanceExcel> attendanceExcelList = new ArrayList<AttendanceExcel>();

        staffList = staffService.findAllFarmStaff();

        choosedDate = choosedDate.plusHours(8);
        outFileName = DateTimeFormatter.ofPattern("yyyy-MM").format(choosedDate);

        LocalDateTime choosedDate1 = choosedDate.with(TemporalAdjusters.firstDayOfMonth());
        choosedDate1 = choosedDate1.withHour(0);
        choosedDate1 = choosedDate1.withMinute(0);
        choosedDate1 = choosedDate1.withSecond(0);
        choosedDate1 = choosedDate1.withNano(0);
        LocalDateTime choosedDate2 = choosedDate.with(TemporalAdjusters.lastDayOfMonth());
        choosedDate2 = choosedDate2.withHour(23);
        choosedDate2 = choosedDate2.withMinute(0);
        choosedDate2 = choosedDate2.withSecond(0);
        choosedDate2 = choosedDate2.withNano(0);
        days = choosedDate2.getDayOfMonth();
        choosedDate2 = choosedDate2.plusHours(1);
        attendanceList = attendanceService.findFarmStaffByChooseTime(choosedDate1, choosedDate2);

        for (Staff staff : staffList) {
            AttendanceExcel attendanceExcel = new AttendanceExcel(0, 12 * days, staff);
            for (Attendance attendance : attendanceList) {
                if (attendance.getStaff().getUsername().equals(staff.getUsername())) {
                    BigDecimal totalWorkingHours = new BigDecimal(Double.toString(attendanceExcel.getWorkingHours()));
                    BigDecimal workingHours = new BigDecimal(Double.toString(attendance.getWorkingHours()));
                    attendanceExcel.setWorkingHours(totalWorkingHours.add(workingHours).doubleValue());
                }
            }

            BigDecimal workingHours = new BigDecimal(Double.toString(attendanceExcel.getWorkingHours()));
            BigDecimal totalWorkingHours = new BigDecimal(Double.toString(attendanceExcel.getTotalWorkingHours()));
            BigDecimal basicSalary = new BigDecimal(Double.toString(attendanceExcel.getStaff().getPosition().getBasicSalary()));
            double totalSalary = workingHours.multiply(basicSalary).doubleValue();
            BigDecimal totalSalary1 = new BigDecimal(Double.toString(totalSalary));
            double trueSalary = totalSalary1.divide(totalWorkingHours, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
            attendanceExcel.setTrueSalary(trueSalary);
            attendanceExcelList.add(attendanceExcel);
        }

        map.put("attendanceExcelList", attendanceExcelList);
        ExcelComponent.exportToExcel(request, response, outFileName, "考勤信息.xls", map);
    }

    @PostMapping("downloadDaySaleExcel")
    // 农场日收入Excel
    public void downloadDaySaleExcel(@RequestBody LocalDateTime choosedDate, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //System.out.println("post success"+choosedDate);

        choosedDate = choosedDate.plusHours(8);

        String outFileName = "";
        outFileName = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(choosedDate);

        LocalDateTime startTime = choosedDate.withHour(0);
        startTime = startTime.withMinute(0);
        startTime = startTime.withSecond(0);
        startTime = startTime.withNano(0);
        LocalDateTime endTime = startTime.plusHours(24);
        List<Sale> saleList = saleService.findByTime(startTime, endTime);

        List<SaleNo> saleNoList = new ArrayList<SaleNo>();
        String temp = "";
        for (Sale sale : saleList) {
            if (!sale.getSaleNo().getNo().equals(temp)) {
                saleNoList.add(sale.getSaleNo());
                temp = sale.getSaleNo().getNo();
            }
        }

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<FarmSaleExcel> farmSaleExcelList = new ArrayList<FarmSaleExcel>();
        for (SaleNo saleNo : saleNoList) {
            FarmSaleExcel farmSaleExcel = new FarmSaleExcel();
            farmSaleExcel.setNo(saleNo.getNo());
            farmSaleExcel.setInsertTime(format.format(saleNo.getInsertTime()));
            farmSaleExcel.setTotalPrice(0);
            for (Sale sale : saleList) {
                if (sale.getSaleNo().getNo().equals(saleNo.getNo())) {
                    BigDecimal amount = new BigDecimal(Double.toString(sale.getAmount()));
                    BigDecimal price = new BigDecimal(Double.toString(sale.getPrice()));
                    BigDecimal totalPrice = new BigDecimal(Double.toString(farmSaleExcel.getTotalPrice()));
                    farmSaleExcel.setTotalPrice(amount.multiply(price).setScale(2, BigDecimal.ROUND_HALF_UP).add(totalPrice).doubleValue());
                }
            }
            farmSaleExcelList.add(farmSaleExcel);
        }

        Map map = new HashMap();
        map.put("farmSaleExcelList", farmSaleExcelList);
        ExcelComponent.exportToExcel(request, response, outFileName, "农场收入.xls", map);
    }

    @PostMapping("downloadMonthSaleExcel")
    // 农场月收入Excel
    public void downloadMonthSaleExcel(@RequestBody LocalDateTime choosedDate, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //System.out.println("post success"+choosedDate);

        choosedDate = choosedDate.plusHours(8);

        String outFileName = "";
        outFileName = DateTimeFormatter.ofPattern("yyyy-MM").format(choosedDate);

        LocalDateTime startTime = choosedDate.with(TemporalAdjusters.firstDayOfMonth());
        startTime = startTime.withHour(0);
        startTime = startTime.withMinute(0);
        startTime = startTime.withSecond(0);
        startTime = startTime.withNano(0);
        LocalDateTime endTime = choosedDate.with(TemporalAdjusters.lastDayOfMonth());
        endTime = endTime.withHour(23);
        endTime = endTime.withMinute(0);
        endTime = endTime.withSecond(0);
        endTime = endTime.withNano(0);
        endTime = endTime.plusHours(1);
        List<Sale> saleList = saleService.findByTime(startTime, endTime);

        List<SaleNo> saleNoList = new ArrayList<SaleNo>();
        String temp = "";
        for (Sale sale : saleList) {
            if (!sale.getSaleNo().getNo().equals(temp)) {
                saleNoList.add(sale.getSaleNo());
                temp = sale.getSaleNo().getNo();
            }
        }

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<FarmSaleExcel> farmSaleExcelList = new ArrayList<FarmSaleExcel>();
        for (SaleNo saleNo : saleNoList) {
            FarmSaleExcel farmSaleExcel = new FarmSaleExcel();
            farmSaleExcel.setNo(saleNo.getNo());
            farmSaleExcel.setInsertTime(format.format(saleNo.getInsertTime()));
            farmSaleExcel.setTotalPrice(0);
            for (Sale sale : saleList) {
                if (sale.getSaleNo().getNo().equals(saleNo.getNo())) {
                    BigDecimal amount = new BigDecimal(Double.toString(sale.getAmount()));
                    BigDecimal price = new BigDecimal(Double.toString(sale.getPrice()));
                    BigDecimal totalPrice = new BigDecimal(Double.toString(farmSaleExcel.getTotalPrice()));
                    farmSaleExcel.setTotalPrice(amount.multiply(price).setScale(2, BigDecimal.ROUND_HALF_UP).add(totalPrice).doubleValue());
                }
            }
            farmSaleExcelList.add(farmSaleExcel);
        }

        Map map = new HashMap();
        map.put("farmSaleExcelList", farmSaleExcelList);
        ExcelComponent.exportToExcel(request, response, outFileName, "农场收入.xls", map);
    }

    @PostMapping("downloadDayPurchaseExcel")
    // 农场日支出
    public void downloadDayPurchaseExcel(@RequestBody LocalDateTime choosedDate, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //System.out.println("post success!" + choosedDate);

        choosedDate = choosedDate.plusHours(8);

        String outFileName = "";
        outFileName = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(choosedDate);

        LocalDateTime startTime = choosedDate.withHour(0);
        startTime = startTime.withMinute(0);
        startTime = startTime.withSecond(0);
        startTime = startTime.withNano(0);
        LocalDateTime endTime = startTime.plusHours(24);
        List<Purchase> purchaseList = purchaseService.findByTime(startTime, endTime);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Map map = new HashMap();
        map.put("purchaseList", purchaseList);
        map.put("format", format);
        ExcelComponent.exportToExcel(request, response, outFileName, "农场支出.xls", map);
    }

    @PostMapping("downloadMonthPurchaseExcel")
    // 农场月支出
    public void downloadMonthPurchaseExcel(@RequestBody LocalDateTime choosedDate, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //System.out.println("post success!" + choosedDate);

        choosedDate = choosedDate.plusHours(8);

        String outFileName = "";
        outFileName = DateTimeFormatter.ofPattern("yyyy-MM").format(choosedDate);

        LocalDateTime startTime = choosedDate.with(TemporalAdjusters.firstDayOfMonth());
        startTime = startTime.withHour(0);
        startTime = startTime.withMinute(0);
        startTime = startTime.withSecond(0);
        startTime = startTime.withNano(0);
        LocalDateTime endTime = choosedDate.with(TemporalAdjusters.lastDayOfMonth());
        endTime = endTime.withHour(23);
        endTime = endTime.withMinute(0);
        endTime = endTime.withSecond(0);
        endTime = endTime.withNano(0);
        endTime = endTime.plusHours(1);
        List<Purchase> purchaseList = purchaseService.findByTime(startTime, endTime);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Map map = new HashMap();
        map.put("purchaseList", purchaseList);
        map.put("format", format);
        ExcelComponent.exportToExcel(request, response, outFileName, "农场支出.xls", map);
    }
}
