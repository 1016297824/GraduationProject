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
// 报修报损表
public class Repair {

    public static final String repairType1 = "报修";
    public static final String RepairType2 = "报损";

    public static final String state1 = "未完成";
    public static final String state2 = "已完成";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ID
    private int id;

    @Column(columnDefinition = "TIMESTAMP NOT NULL " +
            "DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            insertable = false)
    // 创建时间
    private LocalDateTime insertTime;

    // 报修报损类型
    private String repairType;

    // 报修报损内容
    private String content;

    // 报修报损原因
    private String cause;

    // 状态
    private String state;

    // 花费
    private double price;
}
