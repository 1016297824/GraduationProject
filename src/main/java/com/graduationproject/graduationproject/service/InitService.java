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

    @Override
    public void afterPropertiesSet() throws Exception {     //初始化

//        System.out.println("afterPropertiesSet");         //测试是否进入初始化

        if (positionService.findAll().isEmpty()){           //测试Position表是否有数据
            System.out.println("is empty");
        }
    }
}
