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

    // 初始化饲料肥料表
    public void initFertilizer(){

        List<Fertilizer> fertilizerList=new ArrayList<Fertilizer>();

        Fertilizer fertilizer=new Fertilizer("猪饲料", 200.5, 50, "斤", Fertilizer.fertilizerType1);
        Fertilizer fertilizer1=new Fertilizer("");
    }
}
