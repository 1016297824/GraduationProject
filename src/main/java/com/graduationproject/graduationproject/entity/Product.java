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
public class Product {                  // 产品

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;                     // ID

    private String name;                // 产品名称

    private double price;               // 单价

    private String unit;                // 单位

    private double amount;              // 数量

}
