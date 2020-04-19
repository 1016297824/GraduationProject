package com.graduationproject.graduationproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
// 销售表
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ID
    private int id;

    // 数量
    private double amount;

    // 单价
    private double price;

    @ManyToOne
    private SaleNo saleNo;

    @ManyToOne
    private Product product;
}
