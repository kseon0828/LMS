package com.example.demo.src.task.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetTaskListRes {
    private List<GetTaskRes> getTaskRes;


}
