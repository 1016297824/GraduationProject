package com.graduationproject.graduationproject.entity.body;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PageBody {

    private int page;
    private int pages;
    private List<Integer> pageList;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
