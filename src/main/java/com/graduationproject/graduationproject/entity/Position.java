package com.graduationproject.graduationproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Position {                     //职位

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;                         //ID

    private String name;                    //职位名称

    private double basicSalary;             //基本工资

    @OneToOne(mappedBy = "position")
    private User user;
}
