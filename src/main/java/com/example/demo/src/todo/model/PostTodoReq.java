package com.example.demo.src.todo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostTodoReq {
    private boolean complete;
    private String todoName;
}
