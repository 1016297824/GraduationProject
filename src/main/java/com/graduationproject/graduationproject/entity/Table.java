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
public class Table {                    //餐桌

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;                     //ID

    private boolean reserve;            //是否预定

    private int seat;                   //座位数
}
