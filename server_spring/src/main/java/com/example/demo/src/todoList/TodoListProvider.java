package com.example.demo.src.todoList;

import com.example.demo.config.BaseException;
import com.example.demo.src.todoList.model.GetTodoListListRes;
import com.example.demo.src.todoList.model.GetTodoListRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class TodoListProvider {
    private final TodoListDao todoListDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public TodoListProvider(TodoListDao todoListDao, JwtService jwtService) {
        this.todoListDao = todoListDao;
        this.jwtService = jwtService;
    }

    //task리스트 조회하기
    public GetTodoListListRes retrieveTodoList(int userIdx, Date date) throws BaseException {

        try{
            List<GetTodoListRes> getTodoListRes = todoListDao.selectTodoList(userIdx, date);
            GetTodoListListRes getTodoListListRes =new GetTodoListListRes(getTodoListRes);
            return getTodoListListRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}

