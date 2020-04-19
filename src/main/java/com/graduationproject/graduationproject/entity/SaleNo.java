package com.graduationproject.graduationproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
// 单号表
public class SaleNo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ID
    private int id;

    // 单号
    private String no;

    @Column(columnDefinition = "TIMESTAMP NOT NULL " +
            "DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            insertable = false)
    // 创建时间
    private LocalDateTime insertTime;

    @OneToMany(mappedBy = "saleNo",cascade = CascadeType.REMOVE)
    private List<Sale> saleList;
}
