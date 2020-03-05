package com.graduationproject.graduationproject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Getter
@Setter
@NoArgsConstructor
// 员工
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ID
    private int id;

    @Column(unique = true)
    // 用户名
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    // 密码
    private String password;

    // 姓名
    private String name;

    // 电话号码
    private String telNumber;

    @Email
    // 邮箱
    private String email;

    // 家庭住址
    private String address;

    // 身份证号码
    private String idCardNo;

    @ManyToOne
    private Position position;

    public Staff(String username, String password, String name, String telNumber, String email, String address, String idCardNo, Position position) {

        this.username = username;
        this.password = password;
        this.name = name;
        this.telNumber = telNumber;
        this.email = email;
        this.address = address;
        this.idCardNo = idCardNo;
        this.position = position;
    }
}

