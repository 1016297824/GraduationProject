package com.graduationproject.graduationproject.service;

import com.graduationproject.graduationproject.entity.Customer;
import com.graduationproject.graduationproject.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Customer> findAll() {       // 查找所有顾客信息

        return customerRepository.findAll();

    }

    public Customer findByUsername(String name) {           // 通过用户名查找顾客信息

        return customerRepository.findByUsername(name);

    }

    public void initCustomer() {            // 初始化用户信息表

        List<Customer> customerList = new ArrayList<Customer>();

        for (int i = 0; i < 3; i++) {

            Customer customer = new Customer("zk" + (i + 1), passwordEncoder.encode("123456"), "zk", "123456789");
            customerList.add(customer);
        }

        customerRepository.saveAll(customerList);

    }

}
