package com.graduationproject.graduationproject.controller;


import com.graduationproject.graduationproject.component.EncryptorComponent;
import com.graduationproject.graduationproject.entity.DiningTable;
import com.graduationproject.graduationproject.service.CustomerService;
import com.graduationproject.graduationproject.service.DiningTableService;
import com.graduationproject.graduationproject.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EncryptorComponent encryptorComponent;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DiningTableService diningTableService;

    @Autowired
    private ReserveService reserveService;

    @GetMapping("/reserveAdd/initDiningTable")         // 显示桌位
    public Map initDiningTable() {
        //System.out.println("get success");

        int page = 1;
        int pages = 0;
        List<Integer> pageList = new ArrayList<Integer>();
        List<DiningTable> diningTableList = new ArrayList<DiningTable>();

        diningTableList = diningTableService.findAll();

        List<DiningTable> diningTableList1 = reserveService.findDiningTableByNow(LocalDateTime.now(), LocalDateTime.now().plusHours(2));

        if (!diningTableList1.isEmpty()) {
            diningTableList.removeAll(diningTableList1);
        }

        if (diningTableList.size() / 5 > 5) {
            for (int i = 0; i < 5; i++) {
                pageList.add(i + 1);
            }
        } else {
            if (diningTableList.size() % 5 != 0) {
                pages = (diningTableList.size() + 5) / 5;
            } else {
                pages = diningTableList.size() / 5;
            }

            for (int i = 0; i < pages; i++) {
                pageList.add(i + 1);
            }
        }

        diningTableList = diningTableList.subList(0, 5);

        return Map.of("diningTableList", diningTableList, "page", page, "pageList", pageList);
    }
}
