package com.example.demo.src.task;

import com.example.demo.config.BaseException;
import com.example.demo.src.todo.model.GetTodoListRes;
import com.example.demo.src.todo.model.GetTodoRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class TaskProvider {
    private final TaskDao todoDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public TaskProvider(TaskDao todoDao, JwtService jwtService) {
        this.todoDao = todoDao;
        this.jwtService = jwtService;
    }

    //todo리스트 조회하기
    public GetTodoListRes retrieveTodo(int userIdx, Date date) throws BaseException {

        try{
            List<GetTodoRes> getTodoRes = todoDao.selectTodo(userIdx, date);
            GetTodoListRes getTodoListRes =new GetTodoListRes(getTodoRes);
            return getTodoListRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}

