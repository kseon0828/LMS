package com.example.demo.src.todoList.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchTodoListReq {
    private boolean complete;
    private String taskName;
}
