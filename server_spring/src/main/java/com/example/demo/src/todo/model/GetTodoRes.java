package com.example.demo.src.todo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetTodoRes {
    private int complete;
    private String todoName;
    private String todoDate;
    private String endTime;
}
