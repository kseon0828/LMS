package com.example.demo.src.todo;

import com.example.demo.config.BaseException;
import com.example.demo.src.user.*;
import com.example.demo.src.todo.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.demo.config.BaseResponseStatus.USERS_EMPTY_USER_ID;

public class TodoProvider {
    private final TodoDao todoDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public TodoProvider(TodoDao todoDao, JwtService jwtService) {
        this.todoDao = todoDao;
        this.jwtService = jwtService;
    }


    public List<GetTodoRes> retrieveTodo(int day) throws BaseException {

        try{
            List<GetTodoRes> getTodoRes = todoDao.selectTodo(day);
            return getTodoRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public int checkUserExist(int userIdx) throws BaseException{
        try{
            return userDao.checkUserExist(userIdx);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 이메일 확인
    public int checkEmailExist(String email) throws BaseException{
        try{
            return userDao.checkEmailExist(email);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }



}

