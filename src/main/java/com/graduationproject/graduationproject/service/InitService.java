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

    @Override
    public void afterPropertiesSet() throws Exception {     // 初始化
        //System.out.println("afterPropertiesSet");         // 测试是否进入初始化

        if (positionService.findAll().isEmpty()) {
            //System.out.println("position is empty");          // 测试成功控制台输出“position is empty”

            positionService.initPosition();

        }

        if (diningTableService.findAll().isEmpty()) {
            //System.out.println("diningTable is empty");     // 测试成功控制台输出“diningTable is empty”

            diningTableService.initDiningTable();

        }

        if (customerService.findAll().isEmpty()) {
            //System.out.println("customer is empty");     // 测试成功控制台输出“customer is empty”

            customerService.initCustomer();

        }

        if (staffService.findAll().isEmpty()) {
            //System.out.println("staff is empty");     // 测试成功控制台输出“staff is empty”

            staffService.intiStaff();

        }

    }

}
