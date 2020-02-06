package com.graduationproject.graduationproject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer {                                     // 用户

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;                                         // ID

    @Column(unique = true)
    private String username;                                // 用户名

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;                                // 密码

    private String name;                                    // 姓名

    private String telNumber;                               // 电话号码

    public Customer(String username, String password, String name, String telNumber) {

        this.username = username;
        this.password = password;
        this.name = name;
        this.telNumber = telNumber;
    }
}
