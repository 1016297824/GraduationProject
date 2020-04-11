package com.graduationproject.graduationproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
// 产品
public class Product {

    public static final String productType1 = "家禽";
    public static final String productType2 = "鱼类";
    public static final String productType3 = "果蔬";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ID
    private int id;

    // 产品名称
    private String name;

    // 单位
    private String unit;

    // 库存
    private int amount;

    // 安全库存
    private int safeAmount;

    // 产品类型
    private String productType;

    public Product(String name, String unit, int amount, int safeAmount, String productType) {

        this.name = name;
        this.unit = unit;
        this.amount = amount;
        this.safeAmount = safeAmount;
        this.productType = productType;
    }
}
