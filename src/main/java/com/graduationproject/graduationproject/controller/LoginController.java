package com.graduationproject.graduationproject.controller;

import com.graduationproject.graduationproject.component.EncryptorComponent;
import com.graduationproject.graduationproject.entity.Customer;
import com.graduationproject.graduationproject.entity.Position;
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

    // role
    private static final String customer = "tj78hs1hy6s5v";
    private static final String superManager = "6h56s3h478f4";
    private static final String manager = "12yi9i8gnj23";
    private static final String staff = "ghd5dh8f1bq";

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
                //System.out.println("Is customer!");          // 顾客登录测试

                customer = customerService.findByUsername(user.getUsername());

                if (!passwordEncoder.matches(user.getPassword(), customer.getPassword())) {

                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "密码错误！");

                }

                Map map = Map.of("username", customer.getUsername(), "authority", "");
                String token = encryptorComponent.encrypt(map);
                response.setHeader("token", token);
                response.setHeader("role", LoginController.customer);

            } else if (staffService.findByUsername(user.getUsername()) != null) {
                //System.out.println("Is staff!");            // 员工登录测试

                staff = staffService.findByUsername(user.getUsername());

                if (!passwordEncoder.matches(user.getPassword(), staff.getPassword())) {

                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "密码错误！");

                }

                String role = null;

                if (staff.getPosition().getAuthority().equals(Position.superManager)) {

                    role = LoginController.superManager;

                } else if (staff.getPosition().getAuthority().equals(Position.manager)) {

                    role = LoginController.manager;

                } else if (staff.getPosition().getAuthority().equals(Position.staff)) {
                    role = LoginController.staff;
                }

                Map map = Map.of("username", staff.getUsername(), "authority", staff.getPosition().getAuthority());
                String token = encryptorComponent.encrypt(map);
                response.setHeader("token", token);
                response.setHeader("role", role);

            } else {

                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名不存在！");

            }

        }

    }

}
