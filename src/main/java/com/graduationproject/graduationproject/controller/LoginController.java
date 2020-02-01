package com.graduationproject.graduationproject.controller;

import com.graduationproject.graduationproject.component.EncryptorComponent;
import com.graduationproject.graduationproject.entity.Customer;
import com.graduationproject.graduationproject.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EncryptorComponent encryptorComponent;

    @PostMapping("/customerlogin")          //顾客登录
    public void customerLogin(@RequestBody Customer customer, HttpServletResponse response) {
//        System.out.println("post success");       //验证post请求是否成功

        Optional.ofNullable(customerService.findByUserName(customer.getUsername()))
                .or(() -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名不存在！");
                })
                .ifPresentOrElse(cus -> {
                    if (!passwordEncoder.matches(customer.getPassword(), cus.getPassword())) {
                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "密码错误！");
                    }

                    Map map = Map.of("username", cus.getUsername());
                    String token = encryptorComponent.encrypt(map);
                    response.setHeader("token", token);
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名或密码错误！");
                });
    }

}
