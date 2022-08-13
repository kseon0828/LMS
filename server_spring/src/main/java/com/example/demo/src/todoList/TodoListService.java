package com.example.demo.src.todoList;

import com.example.demo.config.BaseException;
import com.example.demo.src.task.model.PostTaskReq;
import com.example.demo.src.task.model.PostTaskRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service
public class TodoListService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TodoListDao taskDao;
    private final TodoListProvider taskProvider;
    private final JwtService jwtService;


    @Autowired
    public TodoListService(TodoListDao taskDao, TodoListProvider taskProvider, JwtService jwtService) {
        this.taskDao = taskDao;
        this.taskProvider = taskProvider;
        this.jwtService = jwtService;

    }

}
