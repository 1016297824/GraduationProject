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
public class Position {                     // 职位

    public static final String superManager = "SuperManager";
    public static final String manager = "Manager";
    public static final String staff = "Staff";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;                         // ID

    private String name;                    // 职位名称

    private String location;                // 办公地点

    private double basicSalary;             // 基本工资

    private String authority;               // 权限

    @OneToMany(mappedBy = "position")
    private List<Staff> staffList;

    public Position(String name, String location, double basicSalary, String authority) {

        this.name = name;
        this.location = location;
        this.basicSalary = basicSalary;
        this.authority = authority;
    }
}
