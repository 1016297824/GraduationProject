package com.graduationproject.graduationproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
// 点餐表
public class Ordering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ID
    private int id;

    // 个数
    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reserve reserve;

    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;
}
