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
public class Menu {                 //菜单

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;                 //ID

    private String name;            //菜名

    private double price;           //价格

    private String unite;           //单位

}
