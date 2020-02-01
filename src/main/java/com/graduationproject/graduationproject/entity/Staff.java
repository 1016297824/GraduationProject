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
public class Staff {                                         //员工

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;                                         //ID

    @Column(unique = true)
    private String username;                                //用户名

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;                                //密码

    private String name;                                    //姓名

    private String telNumber;                              //电话号码

    @Email
    private String email;                                   //邮箱

    private String address;                                 //家庭住址

    private String idCardNo;                                //身份证号码

    @ManyToOne(fetch = FetchType.LAZY)
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

