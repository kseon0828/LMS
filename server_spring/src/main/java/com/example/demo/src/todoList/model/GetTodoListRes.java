package com.example.demo.src.todoList.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetTodoListRes {
    private int complete;
    private String todoName;
    private String todoDate;
    private String endTime;
}
