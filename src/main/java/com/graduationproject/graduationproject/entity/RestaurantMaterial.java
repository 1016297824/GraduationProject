package com.graduationproject.graduationproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
// 餐厅物资表
public class RestaurantMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ID
    private int id;

    @Column(unique = true, nullable = false)
    // 物资名称
    private String name;

    // 库存
    private double amount;

    // 安全库存
    private double safeAmount;

    // 单位
    private String unit;

    @OneToMany(mappedBy = "restaurantMaterial", cascade = CascadeType.REMOVE)
    private List<Purchase> purchaseList;
}
