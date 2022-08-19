package com.example.demo.src.work.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetWorkRes {
    private int complete;
    private String taskName;
    private String endDate;
    private String endTime;
}
