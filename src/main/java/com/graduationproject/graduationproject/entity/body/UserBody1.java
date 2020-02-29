package com.graduationproject.graduationproject.entity.body;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
// 用户修改密码
public class UserBody1 {

    private String username;
    private String password;
    private String newPassword;
    private String newPassword1;
}
