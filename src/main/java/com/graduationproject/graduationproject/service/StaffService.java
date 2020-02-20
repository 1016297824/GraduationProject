package com.graduationproject.graduationproject.service;

import com.graduationproject.graduationproject.entity.Staff;
import com.graduationproject.graduationproject.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private PositionService positionService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 查询所有员工
    public List<Staff> findAll() {

        return staffRepository.findAll();
    }

    // 通过用户名查询员工信息
    public Staff findByUsername(String username) {

        return staffRepository.findByUsername(username);
    }

    // 初始化员工信息表
    public void intiStaff() {

        List<Staff> staffList = new ArrayList<Staff>();

        for (int i = 0; i < positionService.findAll().size(); i++) {
            Staff staff = new Staff("1000" + (i + 1), passwordEncoder.encode("123456"), "zk", "123456789", "zk" + (i + 1) + "@qq.com", "天津市静海区", "1234567890", positionService.findById(i + 1));
            staffList.add(staff);
        }

        staffRepository.saveAll(staffList);
    }
}
