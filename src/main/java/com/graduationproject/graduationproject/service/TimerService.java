package com.graduationproject.graduationproject.service;

import com.graduationproject.graduationproject.entity.Reserve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class TimerService {

    @Autowired
    private ReserveService reserveService;

//    @Scheduled(cron = "0 1 * * * ?")
//    public void cancelReserve() {
//
//        List<Reserve> reserveList = new ArrayList<Reserve>();
//
//        reserveList = reserveService.findAllOverdueReserve(LocalDateTime.now());
//
//        if (!reserveList.isEmpty()) {
//            for (Reserve reserve : reserveList) {
//                reserveService.deleteReserve(reserve);
//            }
//        }
//    }
}
