package com.example.demo.src.todoNew;

import com.example.demo.src.todo.model.GetTodoListRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.todoNew.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.*;


@RestController
@RequestMapping("/todoNew")
public class TodoNewController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final TodoNewProvider todoNewProvider;
    @Autowired
    private final JwtService jwtService;

    public TodoNewController(TodoNewProvider todoNewProvider, JwtService jwtService){
        this.todoNewProvider = todoNewProvider;
        this.jwtService = jwtService;
    }

    @ResponseBody
    @GetMapping("")
    public BaseResponse<TodoListListRes> getTodoList(@RequestParam("date") String date) {

        try{
            SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy-MM-dd");
            // String 타입을 Date 타입으로 변환
            Date formatDate = dtFormat.parse(date);

            int userIdxByJwt = jwtService.getUserIdx();
            System.out.println();

            TodoListListRes todoListListRes = todoNewProvider.retrieveTodo(userIdxByJwt, formatDate);
            return new BaseResponse<>(todoListListRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }



}
