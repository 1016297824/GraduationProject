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
// 菜单
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ID
    private int id;

    // 菜名
    private String name;

    // 价格
    private double price;

    // 单位
    private String unite;

    // 类型
    private String type;

    @OneToMany(mappedBy = "menu")
    private List<Ordering> orderingList;

    public Menu(String name, double price, String unite, String type) {

        this.name = name;
        this.price = price;
        this.unite = unite;
        this.type = type;
    }
}
