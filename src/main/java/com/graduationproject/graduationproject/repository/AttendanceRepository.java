package com.graduationproject.graduationproject.repository;

import com.graduationproject.graduationproject.entity.Attendance;
import com.graduationproject.graduationproject.entity.Customer;
import com.graduationproject.graduationproject.repository.impl.CustomizedRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends CustomizedRepository<Attendance, Integer> {


}
