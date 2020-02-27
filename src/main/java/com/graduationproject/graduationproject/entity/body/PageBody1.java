package com.graduationproject.graduationproject.entity.body;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
// 菜单分页
public class PageBody1 {

    private int page;
    private int pages;
    private List<Integer> pageList;
}
