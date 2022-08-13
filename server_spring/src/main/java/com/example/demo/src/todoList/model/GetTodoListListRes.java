package com.example.demo.src.todoList.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetTodoListListRes {
    private List<GetTodoListRes> getTodoListRes;
}
