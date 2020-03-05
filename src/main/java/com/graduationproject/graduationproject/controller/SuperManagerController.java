package com.graduationproject.graduationproject.controller;

import com.graduationproject.graduationproject.entity.Staff;
import com.graduationproject.graduationproject.entity.body.PageBody1;
import com.graduationproject.graduationproject.service.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/superManager")
public class SuperManagerController {

    @Autowired
    private StaffService staffService;

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

        return Map.of("staffList", staffList, "staffList1", staffList1, "pageBody1", pageBody1);
    }
}
