package com.graduationproject.graduationproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Slf4j
@Component
@Transactional
public class InitService implements InitializingBean {

    @Autowired
    private PositionService positionService;

    @Autowired
    private DiningTableService diningTableService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private MenuService menuService;

    @Override
    // 初始化
    public void afterPropertiesSet() throws Exception {
        // 测试是否进入初始化
        //System.out.println("afterPropertiesSet");

        if (positionService.findAll().isEmpty()) {
            // 测试成功控制台输出“position is empty”
            //System.out.println("position is empty");

            positionService.initPosition();
        }

        if (diningTableService.findAll().isEmpty()) {
            // 测试成功控制台输出“diningTable is empty”
            //System.out.println("diningTable is empty");

            diningTableService.initDiningTable();
        }

        if (customerService.findAll().isEmpty()) {
            // 测试成功控制台输出“customer is empty”
            //System.out.println("customer is empty");

            customerService.initCustomer();
        }

        if (staffService.findAll().isEmpty()) {
            // 测试成功控制台输出“staff is empty”
            //System.out.println("staff is empty");

            staffService.intiStaff();
        }

        if (menuService.findAll().isEmpty()){
            // 测试成功控制台输出“menu is empty”
            //System.out.println("menu is empty");

            menuService.initMenu();
        }
    }
}
