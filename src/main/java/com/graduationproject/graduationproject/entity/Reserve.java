package com.graduationproject.graduationproject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Reserve {              // 预定表

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(unique = true)
    private int no;                 // 订单号

    @Column(columnDefinition = "TIMESTAMP NOT NULL " +
            "DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            insertable = false)
    private LocalDateTime insertTime;       // 创建时间

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime startTime;        // 开始时间

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime endTime;          // 结束时间

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private DiningTable diningTable;
}
