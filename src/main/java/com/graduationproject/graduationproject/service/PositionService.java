package com.graduationproject.graduationproject.service;

import com.graduationproject.graduationproject.entity.Position;
import com.graduationproject.graduationproject.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    // 查询所有职位信息
    public List<Position> findAll() {

        return positionRepository.findAll();
    }

    // 通过ID查询职位信息
    public Position findById(int id) {

        return positionRepository.findById(id);
    }

    // 查询所有管理员和员工职位信息
    public List<Position> findManagerAndStaff() {

        return positionRepository.findManagerAndStaff();
    }

    // 查询所有农场员工职位
    public List<Position> findAllFarmStaff() {

        return positionRepository.findAllFarmStaff();
    }

    // 查询所有餐厅员工职位
    public List<Position> findAllRestaurantStaff() {

        return positionRepository.findAllRestaurantStaff();
    }

    // 初始化职位信息表
    public void initPosition() {

        List<Position> positionList = new ArrayList<Position>();

        Position position = new Position("总经理", "总经理办公室", 10000.00, Position.superManager);
        Position position1 = new Position("农场经理", "农场经理办公室", 8000.00, Position.farmManager);
        Position position2 = new Position("餐厅经理", "餐厅经理办公室", 7000.00, Position.restaurantManager);
        Position position3 = new Position("饲养员", "农场", 5000.00, Position.farmStaff);
        Position position4 = new Position("粮农", "农场", 5000.00, Position.farmStaff);
        Position position5 = new Position("果农", "农场", 5000.00, Position.farmStaff);
        Position position6 = new Position("厨师", "厨房", 5000.00, Position.restaurantStaff);
        Position position7 = new Position("收银员", "前台", 3000.00, Position.restaurantStaff);
        Position position8 = new Position("服务员", "餐厅", 3000.00, Position.restaurantStaff);

        positionList.add(position);
        positionList.add(position1);
        positionList.add(position2);
        positionList.add(position3);
        positionList.add(position4);
        positionList.add(position5);
        positionList.add(position6);
        positionList.add(position7);
        positionList.add(position8);

        positionRepository.saveAll(positionList);
    }
}
