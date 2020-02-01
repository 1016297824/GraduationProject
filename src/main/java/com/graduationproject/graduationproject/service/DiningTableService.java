package com.graduationproject.graduationproject.service;

import com.graduationproject.graduationproject.entity.DiningTable;
import com.graduationproject.graduationproject.repository.DiningTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DiningTableService {

    @Autowired
    private DiningTableRepository diningTableRepository;

    public List<DiningTable> findAll(){     // 查询所有桌位信息

        return diningTableRepository.findAll();

    }

    public void initDiningTable(){          // 初始化桌位表

        List<DiningTable> diningTableList = new ArrayList<DiningTable>();

        for (int i = 0; i < 12; i++) {
            DiningTable diningTable = new DiningTable("4人桌", false, 4);
            diningTableList.add(diningTable);
        }

        for (int i = 0; i < 8; i++) {
            DiningTable diningTable = new DiningTable("8人桌", false, 8);
            diningTableList.add(diningTable);
        }

        for (int i = 0; i < 4; i++) {
            DiningTable diningTable = new DiningTable("12人桌", false, 12);
            diningTableList.add(diningTable);
        }

        diningTableRepository.saveAll(diningTableList);

    }

}
