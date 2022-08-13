package com.example.demo.src.todoList;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.todoList.model.GetTodoListListRes;
import com.example.demo.src.todoList.model.GetTodoListRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/todoList")
public class TodoListController {
    private final TodoListProvider todoListProvider;
    private final TodoListService todoListService;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public TodoListController(TodoListProvider todoListProvider, TodoListService todoListService, JwtService jwtService) {
        this.todoListProvider = todoListProvider;
        this.todoListService = todoListService;
        this.jwtService = jwtService;
    }

    //날짜 과제 조회하기
    @ResponseBody
    @GetMapping("/{date}")
    public BaseResponse<GetTodoListListRes> getTodoListList(@PathVariable("date") String date) {

        try{
            SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy-MM-dd");
            // String 타입을 Date 타입으로 변환
            Date formatDate = dtFormat.parse(date);

            int userIdxByJwt = jwtService.getUserIdx();
            System.out.println();

            GetTodoListListRes getTodoListRes = todoListProvider.retrieveTodoList(userIdxByJwt, formatDate);
            return new BaseResponse<>(getTodoListRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}