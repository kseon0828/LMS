package com.example.demo.src.todo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetTodoListRes {
    private List<GetTodoRes> getTodoRes;


}
