package com.graduationproject.graduationproject.controller;


import com.graduationproject.graduationproject.component.EncryptorComponent;
import com.graduationproject.graduationproject.entity.DiningTable;
import com.graduationproject.graduationproject.entity.body.PageBody;
import com.graduationproject.graduationproject.service.CustomerService;
import com.graduationproject.graduationproject.service.DiningTableService;
import com.graduationproject.graduationproject.service.ReserveService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
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

    @GetMapping("/reserveAdd/initDiningTable")         // 显示默认桌位
    public Map initDiningTable() {
        //System.out.println("get success");

        int page = 1;
        int pages = 0;
        List<Integer> pageList = new ArrayList<Integer>();
        List<DiningTable> diningTableList = new ArrayList<DiningTable>();

        diningTableList = diningTableService.findAll();

        LocalDateTime startTime = LocalDateTime.now();
        startTime = startTime.withMinute(0);
        startTime = startTime.withSecond(0);
        LocalDateTime endTime = LocalDateTime.now().plusHours(2);
        endTime = endTime.withMinute(0);
        endTime = endTime.withSecond(0);
        List<DiningTable> diningTableList1 = reserveService.findDiningTableByTime(startTime, endTime);

        if (!diningTableList1.isEmpty()) {
            diningTableList.removeAll(diningTableList1);
        }

        if (diningTableList.size() / 5 > 5) {
            for (int i = 0; i < 5; i++) {
                pageList.add(i + 1);
            }

            if (diningTableList.size() % 5 != 0) {
                pages = (diningTableList.size() + 5) / 5;
            } else {
                pages = diningTableList.size() / 5;
            }
            diningTableList = diningTableList.subList(0, 5);
        } else {
            if (!diningTableList.isEmpty()) {
                if (diningTableList.size() % 5 != 0) {
                    pages = (diningTableList.size() + 5) / 5;
                } else {
                    pages = diningTableList.size() / 5;
                }

                for (int i = 0; i < pages; i++) {
                    pageList.add(i + 1);
                }

                if (diningTableList.size() < 5) {
                    diningTableList = diningTableList.subList(0, diningTableList.size());
                } else {
                    diningTableList = diningTableList.subList(0, 5);
                }
            }
        }

        PageBody pageBody = new PageBody();
        pageBody.setPage(page);
        pageBody.setPages(pages);
        pageBody.setPageList(pageList);
        pageBody.setStartTime(startTime);
        pageBody.setEndTime(endTime);
        return Map.of("diningTableList", diningTableList, "pageBody", pageBody);
    }

    @PostMapping("/reserveAdd/doPage")
    public Map doPage(@RequestBody PageBody pageBody) {
        System.out.println("post success" + pageBody.getStartTime());

        List<Integer> pageList = new ArrayList<Integer>();
        List<DiningTable> diningTableList = new ArrayList<DiningTable>();

        diningTableList = diningTableService.findAll();
        List<DiningTable> diningTableList1 = reserveService.findDiningTableByTime(pageBody.getStartTime(), pageBody.getEndTime());

        if (!diningTableList1.isEmpty()) {
            diningTableList.removeAll(diningTableList1);
        }

        if (diningTableList.isEmpty()) {
            pageBody.setPage(0);
            pageBody.setPages(0);
            pageBody.setPageList(pageList);
        } else {
            if ((double) diningTableList.size() / 5 > 5.0) {
                if (diningTableList.size() % 5 != 0) {
                    pageBody.setPages((diningTableList.size() + 5) / 5);
                } else {
                    pageBody.setPages(diningTableList.size() / 5);
                }

                if (pageBody.getPage() <= pageBody.getPages()) {
                    if (pageBody.getPage() + 2 > pageBody.getPages()) {
                        for (int i = pageBody.getPages() - 5; i < pageBody.getPages(); i++) {
                            pageList.add(i + 1);
                        }
                    } else {
                        pageList.add(pageBody.getPage() - 2);
                        pageList.add(pageBody.getPage() - 1);
                        pageList.add(pageBody.getPage());
                        pageList.add(pageBody.getPage() + 1);
                        pageList.add(pageBody.getPage() + 2);
                    }
                } else {
                    pageBody.setPage(pageBody.getPages());
                    for (int i = pageBody.getPages() - 5; i < pageBody.getPages(); i++) {
                        pageList.add(i + 1);
                    }
                }
            } else {
                if (diningTableList.size() % 5 != 0) {
                    pageBody.setPages((diningTableList.size() + 5) / 5);
                } else {
                    pageBody.setPages(diningTableList.size() / 5);
                }

                if (pageBody.getPage() <= pageBody.getPages()) {
                    for (int i = 0; i < pageBody.getPages(); i++) {
                        pageList.add(i + 1);
                    }
                } else {
                    pageBody.setPage(pageBody.getPages());
                    for (int i = 0; i < pageBody.getPages(); i++) {
                        pageList.add(i + 1);
                    }
                }
            }

            diningTableList = diningTableList.subList(pageBody.getPage() * 5 - 5, pageBody.getPage() * 5 > diningTableList.size() ? diningTableList.size() : pageBody.getPage() * 5);
            pageBody.setPageList(pageList);
        }

        return Map.of("diningTableList", diningTableList, "pageBody", pageBody);
    }
}
