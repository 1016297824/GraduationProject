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
// 职位
public class Position {

    public static final String superManager = "SuperManager";
    public static final String manager = "Manager";
    public static final String staff = "Staff";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ID
    private int id;

    // 职位名称
    private String name;

    // 办公地点
    private String location;

    // 基本工资
    private double basicSalary;

    // 权限
    private String authority;

    @OneToMany(mappedBy = "position")
    private List<Staff> staffList;

    public Position(String name, String location, double basicSalary, String authority) {

        this.name = name;
        this.location = location;
        this.basicSalary = basicSalary;
        this.authority = authority;
    }
}
