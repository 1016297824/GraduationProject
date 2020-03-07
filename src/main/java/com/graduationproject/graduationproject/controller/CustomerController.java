package com.graduationproject.graduationproject.controller;


import com.graduationproject.graduationproject.component.EncryptorComponent;
import com.graduationproject.graduationproject.entity.*;
import com.graduationproject.graduationproject.entity.body.PageBody;
import com.graduationproject.graduationproject.entity.body.PageBody1;
import com.graduationproject.graduationproject.entity.body.UserBody1;
import com.graduationproject.graduationproject.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    private CustomerService customerService;

    @Autowired
    private DiningTableService diningTableService;

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private OrderingService orderingService;

    @Autowired
    private MenuService menuService;

    @GetMapping("/reserve/getReserve")
    // 获得订单信息 （预定和点餐通用）
    public Map getReserve(@RequestAttribute String username) {
        //System.out.println("get success!");

        // 预定使用
        List<Reserve> reserveList = new ArrayList<Reserve>();
        // 点餐使用
        List<List<Reserve>> reserveLists = new ArrayList<List<Reserve>>();


        reserveList = reserveService.findByCustomerUsername(username);

        int count = reserveList.size();
        int column = 0;
        List<Reserve> reserveList1 = new ArrayList<Reserve>();
        for (Reserve re : reserveList) {
            column++;
            reserveList1.add(re);
            if (column == 3) {
                column = 0;
                reserveLists.add(reserveList1);
                reserveList1 = new ArrayList<Reserve>();
            }
        }

        if (column != 0) {
            reserveLists.add(reserveList.subList(count - column, count));
        }

        return Map.of("reserveList", reserveList, "reserveLists", reserveLists);
    }

    @PostMapping("/reserve/deleteReserve/{no}")
    // 删除订单
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


    @GetMapping("/reserveAdd/initDiningTable")
    // 显示默认桌位
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
        if (diningTableList.size() / 5 >= 5) {
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


    @PostMapping("/reserveAdd/doPage")
    // 桌位分页
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


    @PostMapping("/reserveAdd/{diningTableId}")
    // 添加订单信息
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
            int maxNo = reserveService.getMaxNo() + 1;
            reserve.setNo(String.format("%5d", maxNo).replace(" ", "0"));
        }
        reserve.setCustomer(customer);
        reserve.setDiningTable(diningTable);
        reserve.setStartTime(pageBody.getStartTime());
        reserve.setEndTime(pageBody.getEndTime());

        reserveService.save(reserve);

        return Map.of("message", "预定成功！");
    }

    @GetMapping("/getOrdering/{no}")
    // 获得点餐信息
    public Map getOrdering(@PathVariable String no) {
        //System.out.println("get success!" + no);

        boolean isOrdered;
        int page = 1;
        int pages = 0;
        List<Integer> pageList = new ArrayList<Integer>();
        PageBody1 pageBody1 = new PageBody1();
        List<Ordering> orderingList = new ArrayList<Ordering>();
        List<Menu> menuList = new ArrayList<Menu>();
        List<Menu> menuList1 = new ArrayList<Menu>();
        Reserve reserve = new Reserve();

        menuList = menuService.findAll();
        orderingList = orderingService.findByReserveNo(no);
        reserve = reserveService.findByNo(no);

        if (menuList.size() / 5 >= 5) {
            for (int i = 0; i < 5; i++) {
                pageList.add(i + 1);
            }

            if (menuList.size() % 5 != 0) {
                pages = (menuList.size() + 5) / 5;
            } else {
                pages = menuList.size() / 5;
            }
            menuList1 = menuList.subList(0, 5);
        } else {
            if (!menuList.isEmpty()) {
                if (menuList.size() % 5 != 0) {
                    pages = (menuList.size() + 5) / 5;
                } else {
                    pages = menuList.size() / 5;
                }

                for (int i = 0; i < pages; i++) {
                    pageList.add(i + 1);
                }

                if (menuList.size() < 5) {
                    menuList1 = menuList.subList(0, menuList.size());
                } else {
                    menuList1 = menuList.subList(0, 5);
                }
            }
        }

        pageBody1.setPage(page);
        pageBody1.setPages(pages);
        pageBody1.setPageList(pageList);

        if (orderingList.isEmpty()) {
            isOrdered = false;
        } else {
            isOrdered = true;
        }

        return Map.of("isOrdered", isOrdered,
                "pageBody1", pageBody1,
                "orderingList", orderingList,
                "menuList1", menuList1,
                "menuList", menuList,
                "reserve", reserve);
    }

    @PostMapping("/addOrdering")
    // 添加点餐信息
    public Map addOrdering(@RequestBody List<Ordering> orderingList) {
        //System.out.println("post success!" + orderingList.get(0).getMenu().getName());

        orderingService.saveAll(orderingList);

        return Map.of("message", "提交成功！");
    }

    @PostMapping("/changePassword")
    // 修改密码
    public Map changePassword(@RequestBody UserBody1 userBody1) {
        //System.out.println("post success!" + userBody1.getUsername());

        String message = null;
        Customer customer = new Customer();

        customer = customerService.findByUsername(userBody1.getUsername());
        if (!passwordEncoder.matches(userBody1.getPassword(), customer.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "旧密码错误！");
        } else {
            customer.setPassword(passwordEncoder.encode(userBody1.getNewPassword()));
            customerService.updateCustomer(customer);
            message = "修改成功！";
        }

        return Map.of("message", message);
    }
}
