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

    // 查询所有桌位信息
    public List<DiningTable> findAll() {

        return diningTableRepository.findAll();
    }

    // 通过ID查询桌位信息
    public DiningTable findById(int id){

        return diningTableRepository.findById(id);
    }

    // 初始化桌位表
    public void initDiningTable() {

        List<DiningTable> diningTableList = new ArrayList<DiningTable>();

        for (int i = 0; i < 12; i++) {
            DiningTable diningTable = new DiningTable("4人桌", 4);
            diningTableList.add(diningTable);
        }

        for (int i = 0; i < 8; i++) {
            DiningTable diningTable = new DiningTable("8人桌", 8);
            diningTableList.add(diningTable);
        }

        for (int i = 0; i < 4; i++) {
            DiningTable diningTable = new DiningTable("12人桌", 12);
            diningTableList.add(diningTable);
        }

        diningTableRepository.saveAll(diningTableList);
        diningTableRepository.flush();
    }
}
