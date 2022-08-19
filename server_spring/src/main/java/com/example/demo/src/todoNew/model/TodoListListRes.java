package com.example.demo.src.todoNew.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TodoListListRes {
    private List<TodoListRes> todoListListRes;
}
