package com.graduationproject.graduationproject.service;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.graduationproject.graduationproject.entity.DiningTable;
import com.graduationproject.graduationproject.entity.Position;
import com.graduationproject.graduationproject.repository.DiningTableRepository;
import com.graduationproject.graduationproject.repository.PositionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@Transactional
public class InitService implements InitializingBean {

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private PositionService positionService;

    @Autowired
    private DiningTableRepository diningTableRepository;

    @Autowired
    private DiningTableService diningTableService;

    @Override
    public void afterPropertiesSet() throws Exception {     //初始化

//        System.out.println("afterPropertiesSet");         //测试是否进入初始化

        if (positionService.findAll().isEmpty()) {           //测试Position表是否有数据
//            System.out.println("position is empty");               //测试成功控制台输出“position is empty”

            positionService.initPosition();
        }

        if (diningTableService.findAll().isEmpty()) {
//            System.out.println("diningTable is empty");     //测试成功控制台输出“diningTable is empty”

            diningTableService.initDiningTable();
        }
    }
}
