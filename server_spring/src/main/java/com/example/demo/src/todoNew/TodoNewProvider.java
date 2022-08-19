package com.example.demo.src.todoNew;

import com.example.demo.config.BaseException;
import com.example.demo.src.todoNew.TodoNewDao;
import com.example.demo.src.todoNew.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

import java.util.List;
import java.util.Date;

@Service
public class TodoNewProvider {
    private final TodoNewDao todoNewDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public TodoNewProvider(TodoNewDao todoNewDao, JwtService jwtService) {
        this.todoNewDao = todoNewDao;
        this.jwtService = jwtService;
    }

    public TodoListListRes retrieveTodo(int userIdx, Date date) throws BaseException {

        try{
            List<TodoListRes> todoListRes = todoNewDao.selectTodo(userIdx, date);
            TodoListListRes todoListListRes =new TodoListListRes(todoListRes);
            return todoListListRes;

        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
