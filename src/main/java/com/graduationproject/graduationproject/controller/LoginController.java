package com.graduationproject.graduationproject.controller;

import com.graduationproject.graduationproject.component.EncryptorComponent;
import com.graduationproject.graduationproject.entity.Customer;
import com.graduationproject.graduationproject.entity.Position;
import com.graduationproject.graduationproject.entity.Staff;
import com.graduationproject.graduationproject.entity.body.UserBody;
import com.graduationproject.graduationproject.service.CustomerService;
import com.graduationproject.graduationproject.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    // role
    private static final String customer = "Customer";
    private static final String superManager = "SuperManager";
    private static final String manager = "Manager";
    private static final String staff = "Staff";

    @Autowired
    private CustomerService customerService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EncryptorComponent encryptorComponent;

    @PostMapping("/login")
    // 用户登录
    public void customerLogin(@RequestBody UserBody userBody, HttpServletResponse response) {
        // 验证post请求是否成功
        //System.out.println("post success");

        if (userBody != null) {
            //System.out.println("User not null!");

            Customer customer = null;
            Staff staff = null;

            if (customerService.findByUsername(userBody.getUsername()) != null) {
                // 顾客登录测试
                //System.out.println("Is customer!");

                customer = customerService.findByUsername(userBody.getUsername());

                if (!passwordEncoder.matches(userBody.getPassword(), customer.getPassword())) {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "密码错误！");
                }

                Map map = Map.of("authority", "customer", "username", customer.getUsername());
                String token = encryptorComponent.encrypt(map);
                response.setHeader("token", token);
                response.setHeader("role", LoginController.customer);
                response.setHeader("username", customer.getUsername());

            } else if (staffService.findByUsername(userBody.getUsername()) != null) {
                // 员工登录测试
                //System.out.println("Is staff!");

                staff = staffService.findByUsername(userBody.getUsername());

                if (!passwordEncoder.matches(userBody.getPassword(), staff.getPassword())) {
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

                Map map = Map.of("authority", staff.getPosition().getAuthority());
                String token = encryptorComponent.encrypt(map);
                response.setHeader("token", token);
                response.setHeader("role", role);
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名不存在！");
            }
        }
    }

    @PostMapping("/register")
    // 用户注册
    public Map customerRegister(@RequestBody Customer customer) {
        // 验证post请求是否成功
        //System.out.println("post success");

        String message = null;

        if (customerService.findByUsername(customer.getUsername()) != null) {
            message = "手机号已注册！";
            return Map.of("message", message);
        }

        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerService.addCustomer(customer);
        message = "注册成功！";

        return Map.of("message", message);
    }
}
