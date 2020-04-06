package com.graduationproject.graduationproject.controller;

import com.graduationproject.graduationproject.entity.DiningTable;
import com.graduationproject.graduationproject.entity.Ordering;
import com.graduationproject.graduationproject.entity.OrderingComplete;
import com.graduationproject.graduationproject.entity.Reserve;
import com.graduationproject.graduationproject.entity.body.PageBody;
import com.graduationproject.graduationproject.entity.body.PageBody1;
import com.graduationproject.graduationproject.service.OrderingCompleteService;
import com.graduationproject.graduationproject.service.OrderingService;
import com.graduationproject.graduationproject.service.ReserveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/restaurantStaff")
// 餐厅员工功能
public class RestaurantStaffController {

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private OrderingService orderingService;

    @Autowired
    private OrderingCompleteService orderingCompleteService;

    @GetMapping("/getReserve")
    public Map getReserve() {
        //System.out.println("get success!");

        int page = 1;
        int pages = 0;
        List<Integer> pageList = new ArrayList<Integer>();

        List<Reserve> reserveList = new ArrayList<Reserve>();
        reserveList = reserveService.findAll();

        if (reserveList.size() / 5 >= 5) {
            for (int i = 0; i < 5; i++) {
                pageList.add(i + 1);
            }

            if (reserveList.size() % 5 != 0) {
                pages = (reserveList.size() + 5) / 5;
            } else {
                pages = reserveList.size() / 5;
            }
            reserveList = reserveList.subList(0, 5);
        } else {
            if (!reserveList.isEmpty()) {
                if (reserveList.size() % 5 != 0) {
                    pages = (reserveList.size() + 5) / 5;
                } else {
                    pages = reserveList.size() / 5;
                }

                for (int i = 0; i < pages; i++) {
                    pageList.add(i + 1);
                }

                if (reserveList.size() < 5) {
                    reserveList = reserveList.subList(0, reserveList.size());
                } else {
                    reserveList = reserveList.subList(0, 5);
                }
            }
        }

        PageBody1 pageBody1 = new PageBody1();
        pageBody1.setPage(page);
        pageBody1.setPages(pages);
        pageBody1.setPageList(pageList);

        return Map.of("reserveList", reserveList, "pageBody1", pageBody1);
    }

    @PostMapping("/doPage")
    public Map doPage(@RequestBody PageBody1 pageBody1) {
        //System.out.println("post success!" + pageBody1.getPage());

        List<Integer> pageList = new ArrayList<Integer>();
        List<Reserve> reserveList = new ArrayList<Reserve>();

        reserveList = reserveService.findAll();
        if (reserveList.isEmpty()) {
            pageBody1.setPage(0);
            pageBody1.setPages(0);
            pageBody1.setPageList(pageList);
        } else {
            if ((double) reserveList.size() / 5 > 5.0) {
                if (reserveList.size() % 5 != 0) {
                    pageBody1.setPages((reserveList.size() + 5) / 5);
                } else {
                    pageBody1.setPages(reserveList.size() / 5);
                }

                if (pageBody1.getPage() <= pageBody1.getPages()) {
                    if (pageBody1.getPage() + 2 > pageBody1.getPages()) {
                        for (int i = pageBody1.getPages() - 5; i < pageBody1.getPages(); i++) {
                            pageList.add(i + 1);
                        }
                    } else {
                        pageList.add(pageBody1.getPage() - 2);
                        pageList.add(pageBody1.getPage() - 1);
                        pageList.add(pageBody1.getPage());
                        pageList.add(pageBody1.getPage() + 1);
                        pageList.add(pageBody1.getPage() + 2);
                    }
                } else {
                    pageBody1.setPage(pageBody1.getPages());
                    for (int i = pageBody1.getPages() - 5; i < pageBody1.getPages(); i++) {
                        pageList.add(i + 1);
                    }
                }
            } else {
                if (reserveList.size() % 5 != 0) {
                    pageBody1.setPages((reserveList.size() + 5) / 5);
                } else {
                    pageBody1.setPages(reserveList.size() / 5);
                }

                if (pageBody1.getPage() <= pageBody1.getPages()) {
                    for (int i = 0; i < pageBody1.getPages(); i++) {
                        pageList.add(i + 1);
                    }
                } else {
                    pageBody1.setPage(pageBody1.getPages());
                    for (int i = 0; i < pageBody1.getPages(); i++) {
                        pageList.add(i + 1);
                    }
                }
            }

            reserveList = reserveList.subList(pageBody1.getPage() * 5 - 5, pageBody1.getPage() * 5 > reserveList.size() ? reserveList.size() : pageBody1.getPage() * 5);
            pageBody1.setPageList(pageList);
        }

        return Map.of("reserveList", reserveList, "pageBody1", pageBody1);
    }

    @GetMapping("/deleteReserve/{no}")
    public Map deleteReserve(@PathVariable String no,
                             @RequestAttribute String username) {
        //System.out.println("get success!" + no + username);

        String message = "";

        Reserve reserve = reserveService.findByNo(no);
        if (reserve != null) {
            reserveService.deleteReserve(reserve);
            message = "删除成功！";
        }

        return Map.of("message", message);
    }

    @GetMapping("getOrdering/{no}")
    public Map getOrdering(@PathVariable String no) {
        //System.out.println("get success!" + no);

        List<Ordering> orderingList = new ArrayList<Ordering>();
        orderingList = orderingService.findByReserveNo(no);

        return Map.of("orderingList", orderingList);
    }

    @GetMapping("settleAccounts/{no}")
    public Map settleAccounts(@PathVariable String no) {
        //System.out.println("get success!" + no);

        String message = "";
        Reserve reserve = new Reserve();
        List<Ordering> orderingList = new ArrayList<Ordering>();
        OrderingComplete orderingComplete = new OrderingComplete();

        reserve = reserveService.findByNo(no);
        orderingList = orderingService.findByReserveNo(no);

        double totalPrice = 0.0;
        for (Ordering ordering : orderingList) {
            totalPrice += ordering.getCount() * ordering.getMenu().getPrice();
        }

        orderingComplete.setTotalPrice(totalPrice);
        orderingComplete.setReserveNo(no);
        orderingComplete.setCustomer(reserve.getCustomer());
        orderingCompleteService.save(orderingComplete);

        reserveService.deleteReserve(reserve);
        message = "已结算！";

        return Map.of("message", message);
    }
}
