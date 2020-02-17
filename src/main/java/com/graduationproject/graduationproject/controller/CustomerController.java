package com.graduationproject.graduationproject.controller;


import com.graduationproject.graduationproject.component.EncryptorComponent;
import com.graduationproject.graduationproject.entity.Customer;
import com.graduationproject.graduationproject.entity.DiningTable;
import com.graduationproject.graduationproject.entity.Reserve;
import com.graduationproject.graduationproject.entity.body.PageBody;
import com.graduationproject.graduationproject.service.CustomerService;
import com.graduationproject.graduationproject.service.DiningTableService;
import com.graduationproject.graduationproject.service.ReserveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    // 获得订单信息
    @GetMapping("/reserve/getReserve")
    public Map getReserve(@RequestAttribute String username) {
        //System.out.println("get success!");

        List<Reserve> reserveList = new ArrayList<Reserve>();

        reserveList = reserveService.findByCustomerUsername(username);

        return Map.of("reserveList", reserveList);
    }

    // 删除订单
    @PostMapping("/reserve/deleteReserve/{no}")
    public Map deleteReserve(@PathVariable String no,
                             @RequestAttribute String username) {
        //System.out.println("post success " + no);

        Reserve reserve = new Reserve();
        List<Reserve> reserveList = new ArrayList<Reserve>();

        reserve = reserveService.findByNo(no);
        reserveService.deleteReserve(reserve);

        reserveList = reserveService.findByCustomerUsername(username);

        return Map.of("reserveList", reserveList);
    }

    // 显示默认桌位
    @GetMapping("/reserveAdd/initDiningTable")
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
        LocalDateTime endTime = LocalDateTime.now().plusHours(1);
        endTime = endTime.withMinute(0);
        endTime = endTime.withSecond(0);
        List<DiningTable> diningTableList1 = reserveService.findDiningTableByTime(startTime, endTime);

        //log.debug("{}", diningTableList);
        if (!diningTableList1.isEmpty()) {
            diningTableList.removeAll(diningTableList1);
        }

        //log.debug("{}", diningTableList1);
        //log.debug("{}", diningTableList);
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

        //System.out.println(startTime);
        PageBody pageBody = new PageBody();
        pageBody.setPage(page);
        pageBody.setPages(pages);
        pageBody.setPageList(pageList);
        pageBody.setStartTime(startTime);
        pageBody.setEndTime(endTime);
        return Map.of("diningTableList", diningTableList, "pageBody", pageBody);
    }

    // 桌位分页
    @PostMapping("/reserveAdd/doPage")
    public Map doPage(@RequestBody PageBody pageBody) {
        //System.out.println("post success " + pageBody.getStartTime());

        List<Integer> pageList = new ArrayList<Integer>();
        List<DiningTable> diningTableList = new ArrayList<DiningTable>();

        diningTableList = diningTableService.findAll();
        List<DiningTable> diningTableList1 = reserveService.findDiningTableByTime(pageBody.getStartTime(), pageBody.getEndTime());
        //System.out.println(diningTableList1.size());

        //log.debug("{}", diningTableList);
        if (!diningTableList1.isEmpty()) {
            diningTableList.removeAll(diningTableList1);
        }

        //log.debug("{}", diningTableList1);
        //log.debug("{}", diningTableList);
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

    // 添加订单信息
    @PostMapping("/reserveAdd/{diningTableId}")
    public Map reserve(@PathVariable int diningTableId,
                       @RequestAttribute String username,
                       @RequestBody PageBody pageBody,
                       HttpServletRequest request) {
        //System.out.println("post success! " + diningTableId + username + " " + pageBody.getPage());

        Reserve reserve = new Reserve();

        Customer customer = customerService.findByUsername(username);
        DiningTable diningTable = diningTableService.findById(diningTableId);
        int count = reserveService.getCount();

        if (count == 0) {
            reserve.setNo("00001");
        } else {
            int maxNo = reserveService.getMaxNo();
            reserve.setNo("0000" + (maxNo + 1));
        }
        reserve.setCustomer(customer);
        reserve.setDiningTable(diningTable);
        reserve.setStartTime(pageBody.getStartTime());
        reserve.setEndTime(pageBody.getEndTime());

        reserveService.save(reserve);

        return Map.of("message", "预定成功！");
    }
}
