package com.graduationproject.graduationproject.repository;

import com.graduationproject.graduationproject.entity.DiningTable;
import com.graduationproject.graduationproject.entity.Reserve;
import com.graduationproject.graduationproject.repository.impl.CustomizedRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReserveRepository extends CustomizedRepository<Reserve, Integer> {

    @Query("select re from Reserve re where re.customer.username=:username")
    List<Reserve> findByCustomerUsername(@Param("username") String username);

    @Query("select re from Reserve re where re.no=:no")
    Reserve findByNo(@Param("no") String no);

    @Query("select re.diningTable from Reserve re " +
            "where (re.startTime<=:startTime and re.endTime>:startTime)" +
            "or (re.startTime<:endTime and re.endTime>=:endTime) " +
            "or (re.startTime>=:startTime and re.endTime<=:endTime)")
    List<DiningTable> findDiningTableByTime(@Param("startTime") LocalDateTime startTime,
                                            @Param("endTime") LocalDateTime endTime);

    @Query("select count(re) from Reserve re")
    int getCount();

    @Query("select max(re.no) from Reserve re")
    int getMaxNo();
}
