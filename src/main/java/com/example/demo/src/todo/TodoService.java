package com.example.demo.src.todo;

import com.example.demo.config.BaseException;
import com.example.demo.src.todo.*;
import com.example.demo.src.todo.model.*;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service
public class TodoService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TodoDao todoDao;
    private final TodoProvider todoProvider;
    private final JwtService jwtService;


    @Autowired
    public TodoService(TodoDao todoDao, TodoProvider todoProvider, JwtService jwtService) {
        this.todoDao = todoDao;
        this.todoProvider = todoProvider;
        this.jwtService = jwtService;

    }

    // todo리스트 생성
    public PostTodoRes createTodo(int userIdx, Date date, PostTodoReq postTodoReq) throws BaseException {

        try{
            int todoIdx = todoDao.insertTodo(userIdx, date, postTodoReq.getTodoName());
            return new PostTodoRes(todoIdx);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
