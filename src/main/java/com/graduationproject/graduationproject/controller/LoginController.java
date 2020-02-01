package com.graduationproject.graduationproject.controller;

import com.graduationproject.graduationproject.component.EncryptorComponent;
import com.graduationproject.graduationproject.entity.Customer;
import com.graduationproject.graduationproject.entity.Staff;
import com.graduationproject.graduationproject.entity.User;
import com.graduationproject.graduationproject.service.CustomerService;
import com.graduationproject.graduationproject.service.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EncryptorComponent encryptorComponent;

    @PostMapping("/login")          // 用户登录
    public void customerLogin(@RequestBody User user, HttpServletResponse response) {
        //System.out.println("post success");       // 验证post请求是否成功

        if (user != null) {
            //System.out.println("User not null!");

            Customer customer = null;
            Staff staff = null;

            if (customerService.findByUsername(user.getUsername()) != null) {

                customer = customerService.findByUsername(user.getUsername());

                if (!passwordEncoder.matches(user.getPassword(), customer.getPassword())) {

                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "密码错误！");

                }

                Map map = Map.of("username", customer.getUsername(),"role","customer","authority", "");
                String token = encryptorComponent.encrypt(map);
                response.setHeader("token", token);

            } else if (staffService.findByUsername(user.getUsername()) != null) {

            } else {

                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名不存在！");

            }

        }

//        Optional.ofNullable(customerService.findByUsername(user.getUsername()))
//                .or(() -> {
//                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名不存在！");
//                })
//                .or(staffService.findByUsername(user.getUsername()))
//                .ifPresentOrElse(u -> {
//                    if (!passwordEncoder.matches(user.getPassword(), user.getPassword())) {
//                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "密码错误！");
//                    }
//
//                    Map map = Map.of("username", cus.getUsername());
//                    String token = encryptorComponent.encrypt(map);
//                    response.setHeader("token", token);
//                }, () -> {
//                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名或密码错误！");
//                });


    }

}
