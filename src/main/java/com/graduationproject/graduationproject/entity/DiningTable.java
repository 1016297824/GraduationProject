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
public class DiningTable {                    // 餐桌

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;                           // ID

    private String type;                      // 类型

    private int seat;                         // 座位数

    @OneToMany(mappedBy = "diningTable")
    private List<Reserve> reserveList;

    public DiningTable(String type, int seat) {

        this.type = type;
        this.seat = seat;
    }
}

