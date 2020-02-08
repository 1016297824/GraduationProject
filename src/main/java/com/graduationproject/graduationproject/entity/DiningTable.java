package com.graduationproject.graduationproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DiningTable {                    // 餐桌

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;                           // ID

    private String type;                      // 类型

    private boolean reserve;                  // 是否预定

    private int seat;                         // 座位数

    public DiningTable(String type, boolean reserve, int seat) {

        this.type = type;
        this.reserve = reserve;
        this.seat = seat;
    }
}

