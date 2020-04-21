package com.graduationproject.graduationproject.repository;

import com.graduationproject.graduationproject.entity.Attendance;
import com.graduationproject.graduationproject.entity.Customer;
import com.graduationproject.graduationproject.repository.impl.CustomizedRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AttendanceRepository extends CustomizedRepository<Attendance, Integer> {

    @Query("from  Attendance a")
    List<Attendance> findAll();

    @Query("select a from Attendance a " +
            "where a.insertTime >= :choosedDate1 and a.insertTime < :choosedDate2 " +
            "and a.staff.position.authority in  ('FarmManager','RestaurantManager')")
    List<Attendance> findManagerByChooseTime(@Param("choosedDate1") LocalDateTime choosedDate1,
                                             @Param("choosedDate2") LocalDateTime choosedDate2);

    @Query("select a from Attendance a " +
            "where a.insertTime >= :choosedDate1 and a.insertTime < :choosedDate2 " +
            "and a.staff.position.authority = 'FarmStaff'")
    List<Attendance> findFarmStaffByChooseTime(@Param("choosedDate1") LocalDateTime choosedDate1,
                                               @Param("choosedDate2") LocalDateTime choosedDate2);

    @Query("select a from Attendance a " +
            "where a.insertTime >= :choosedDate1 and a.insertTime < :choosedDate2 " +
            "and a.staff.position.authority = 'RestaurantStaff'")
    List<Attendance> findRestaurantStaffByChooseTime(@Param("choosedDate1") LocalDateTime choosedDate1,
                                                     @Param("choosedDate2") LocalDateTime choosedDate2);
}
