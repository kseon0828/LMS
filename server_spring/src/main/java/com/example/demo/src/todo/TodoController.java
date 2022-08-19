package com.example.demo.src.todo;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.todo.model.GetTodoListRes;
import com.example.demo.src.todo.model.GetTodoRes;
import com.example.demo.src.todo.model.PostTodoReq;
import com.example.demo.src.todo.model.PostTodoRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/todo")
public class TodoController {
    private final TodoProvider todoProvider;
    private final TodoService todoService;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public TodoController(TodoProvider todoProvider, TodoService todoService, JwtService jwtService) {
        this.todoProvider = todoProvider;
        this.todoService = todoService;
        this.jwtService = jwtService;
    }


    //날짜 todo리스트 조회하기
    @ResponseBody
    @GetMapping("/{date}")
    public BaseResponse<GetTodoListRes> getTodoList(@PathVariable("date") String date) {

        try{
            SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy-MM-dd");
            // String 타입을 Date 타입으로 변환
            Date formatDate = dtFormat.parse(date);

            int userIdxByJwt = jwtService.getUserIdx();
            System.out.println();

            GetTodoListRes getTodoRes = todoProvider.retrieveTodo(userIdxByJwt, formatDate);
            return new BaseResponse<>(getTodoRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    //todo리스트 생성하기
    @ResponseBody
    @PostMapping("/{todoDate}")
    //public BaseResponse<PostTodoRes> getUserByIdx(@PathVariable("date") String date, @RequestBody PostTodoReq postTodoReq) {
    public BaseResponse<PostTodoRes> getUserByIdx(@PathVariable("todoDate") String todoDate, @RequestParam("todoName") String postTodoReq) {

        try{
            SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
            // String 타입을 Date 타입으로 변환
            Date formatDate = dtFormat.parse(todoDate);

            int userIdxByJwt = jwtService.getUserIdx();

            PostTodoRes postTodoRes = todoService.createTodo(userIdxByJwt, formatDate, postTodoReq);
            return new BaseResponse<>(postTodoRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    //todo리스트 수정하기
    @ResponseBody
    @PatchMapping("/{todoIdx}")
    public BaseResponse<String> modifyTodo(@PathVariable("todoIdx") int todoIdx, @RequestParam("todoName") PostTodoReq postTodoReq) {
    //public BaseResponse<String> modifyTodo(@PathVariable ("todoIdx") int todoIdx, @RequestBody PostTodoReq postTodoReq) {
        try{
            int userIdxByJwt = jwtService.getUserIdx();

            todoService.modifyTodo(userIdxByJwt, todoIdx, postTodoReq);
            String result = " To Do List 수정을 완료하였습니다.";
            return new BaseResponse<>(result);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //todo리스트 삭제
    @ResponseBody
    @PatchMapping("/{todoIdx}/status")
    public BaseResponse<String> deleteTodo(@PathVariable ("todoIdx") int todoIdx) {
        try{
            todoService.deleteTodo(todoIdx);
            String result = "To Do List 삭제를 성공했습니다.";
            return new BaseResponse<>(result);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


}