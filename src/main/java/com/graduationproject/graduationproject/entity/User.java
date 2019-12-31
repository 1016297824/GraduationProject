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
public class User {                                         //员工

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;                                         //ID

    @Column(unique = true)
    private String username;                                //用户名

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;                                //密码

    private String name;                                    //姓名

    private  String telnumber;                              //电话号码

    @Email
    private String email;                                   //邮箱

    private String address;                                 //家庭住址

    private String idcardno;                                //身份证号码

    @OneToOne
    private Position position;
}
