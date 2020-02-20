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
// 餐桌
public class DiningTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ID
    private int id;

    // 类型
    private String type;

    // 座位数
    private int seat;

    @OneToMany(mappedBy = "diningTable")
    private List<Reserve> reserveList;

    public DiningTable(String type, int seat) {

        this.type = type;
        this.seat = seat;
    }
}

