package com.graduationproject.graduationproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
// 饲料肥料表
public class Fertilizer {

    public static final String fertilizerType1 = "饲料";
    public static final String fertilizerType2 = "肥料";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ID
    private int id;

    @Column(unique = true, nullable = false)
    // 产品名称
    private String name;

    // 库存
    private double amount;

    // 安全库存
    private double safeAmount;

    // 单位
    private String unit;

    // 类型
    private String fertilizerType;

    public Fertilizer(String name, double amount, double safeAmount, String unit, String fertilizerType) {

        this.name = name;
        this.amount = amount;
        this.safeAmount = safeAmount;
        this.unit = unit;
        this.fertilizerType = fertilizerType;
    }
}
