package com.graduationproject.graduationproject.service;

import com.graduationproject.graduationproject.entity.Fertilizer;
import com.graduationproject.graduationproject.entity.Product;
import com.graduationproject.graduationproject.repository.FertilizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FertilizerService {

    @Autowired
    private FertilizerRepository fertilizerRepository;

    // 查询所有饲料化肥信息
    public List<Fertilizer> findAll() {

        return fertilizerRepository.findAll();
    }

    // 通过类型查找饲料化肥信息
    public List<Fertilizer> findByFertilizerType(String fertilizerType) {

        return fertilizerRepository.findByFertilizerType(fertilizerType);
    }

    // 通过名称查询饲料肥料信息
    public Fertilizer findByName(String name) {

        return fertilizerRepository.findByName(name);
    }

    // 保存饲料肥料信息
    public void save(Fertilizer fertilizer) {

        fertilizerRepository.save(fertilizer);
    }

    // 初始化饲料肥料表
    public void initFertilizer() {

        List<Fertilizer> fertilizerList = new ArrayList<Fertilizer>();

        Fertilizer fertilizer = new Fertilizer("猪饲料", 200.5, 50, "斤", Fertilizer.fertilizerType1);
        Fertilizer fertilizer1 = new Fertilizer("鸡饲料", 200.5, 50, "斤", Fertilizer.fertilizerType1);
        Fertilizer fertilizer2 = new Fertilizer("鸭子饲料", 200.5, 50, "斤", Fertilizer.fertilizerType1);
        Fertilizer fertilizer3 = new Fertilizer("鹅饲料", 200.5, 50, "斤", Fertilizer.fertilizerType1);
        Fertilizer fertilizer4 = new Fertilizer("兔子饲料", 200.5, 50, "斤", Fertilizer.fertilizerType1);
        Fertilizer fertilizer5 = new Fertilizer("鹌鹑饲料", 200.5, 50, "斤", Fertilizer.fertilizerType1);

        Fertilizer fertilizer6 = new Fertilizer("碳酸氢铵", 50.6, 20, "斤", Fertilizer.fertilizerType2);
        Fertilizer fertilizer7 = new Fertilizer("尿素", 50.6, 20, "斤", Fertilizer.fertilizerType2);
        Fertilizer fertilizer8 = new Fertilizer("磷酸钙", 50.6, 20, "斤", Fertilizer.fertilizerType2);
        Fertilizer fertilizer9 = new Fertilizer("钙镁磷肥", 50.6, 20, "斤", Fertilizer.fertilizerType2);
        Fertilizer fertilizer10 = new Fertilizer("氯化钾", 50.6, 20, "斤", Fertilizer.fertilizerType2);
        Fertilizer fertilizer11 = new Fertilizer("硫酸钾", 50.6, 20, "斤", Fertilizer.fertilizerType2);

        fertilizerList.add(fertilizer);
        fertilizerList.add(fertilizer1);
        fertilizerList.add(fertilizer2);
        fertilizerList.add(fertilizer3);
        fertilizerList.add(fertilizer4);
        fertilizerList.add(fertilizer5);
        fertilizerList.add(fertilizer6);
        fertilizerList.add(fertilizer7);
        fertilizerList.add(fertilizer8);
        fertilizerList.add(fertilizer9);
        fertilizerList.add(fertilizer10);
        fertilizerList.add(fertilizer11);

        fertilizerRepository.saveAll(fertilizerList);
    }
}
