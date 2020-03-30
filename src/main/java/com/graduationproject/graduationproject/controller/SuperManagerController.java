package com.graduationproject.graduationproject.controller;

import com.graduationproject.graduationproject.entity.Position;
import com.graduationproject.graduationproject.entity.Staff;
import com.graduationproject.graduationproject.entity.body.PageBody1;
import com.graduationproject.graduationproject.entity.body.UserBody1;
import com.graduationproject.graduationproject.service.PositionService;
import com.graduationproject.graduationproject.service.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/superManager")
public class SuperManagerController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StaffService staffService;

    @Autowired
    private PositionService positionService;

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

        staffList = staffService.findByPositionAuthorityManagerStaff();
        staffList.addAll(staffService.findByPositionAuthorityIsNull());

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

        List<Position> positionList = positionService.findManagerAndStaff();

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
}
