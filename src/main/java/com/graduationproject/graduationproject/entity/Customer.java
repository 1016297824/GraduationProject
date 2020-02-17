package com.graduationproject.graduationproject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
// 用户
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ID
    private int id;

    @Column(unique = true,nullable = false)
    // 用户名
    private String username;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    // 密码
    private String password;

    // 姓名
    private String name;

    @OneToMany(mappedBy = "customer")
    private List<Reserve> reserveList;

    public Customer(String username, String password, String name) {

        this.username = username;
        this.password = password;
        this.name = name;
    }
}
