package com.graduationproject.graduationproject.service;

import com.graduationproject.graduationproject.entity.RestaurantMaterial;
import com.graduationproject.graduationproject.repository.RestaurantMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RestaurantMaterialService {

    @Autowired
    private RestaurantMaterialRepository restaurantMaterialRepository;

    // 保存餐厅物资信息
    public void save(RestaurantMaterial restaurantMaterial) {

        restaurantMaterialRepository.save(restaurantMaterial);
    }

    // 查询所有餐厅物资信息
    public List<RestaurantMaterial> findAll() {

        return restaurantMaterialRepository.findAll();
    }

    // 通过名称查找餐厅物资信息
    public RestaurantMaterial findByName(String name) {

        return restaurantMaterialRepository.findByName(name);
    }

    // 删除餐厅物资信息
    public void delete(RestaurantMaterial restaurantMaterial) {

        restaurantMaterialRepository.delete(restaurantMaterial);
    }
}
