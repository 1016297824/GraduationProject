package com.graduationproject.graduationproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
// 预定表
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    // 订单号
    private String no;

    @Column(columnDefinition = "TIMESTAMP NOT NULL " +
            "DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            insertable = false)
    // 创建时间
    private LocalDateTime insertTime;

    @Column(nullable = false)
    // 开始时间
    private LocalDateTime startTime;

    @Column(nullable = false)
    // 结束时间
    private LocalDateTime endTime;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private DiningTable diningTable;
}
