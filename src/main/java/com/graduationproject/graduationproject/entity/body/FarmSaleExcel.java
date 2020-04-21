package com.graduationproject.graduationproject.entity.body;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
// 农场销售Excel
public class FarmSaleExcel {

    private String no;
    private String insertTime;
    private double totalPrice;
}
