package com.example.demo.src.todo;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.todo.model.GetTodoRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.config.BaseResponseStatus.*;

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
    @GetMapping("/{day}")
    public BaseResponse<GetTodoRes> getTodoList(@PathVariable("day")int day) {

        try{
            int userIdxByJwt = jwtService.getUserIdx();

            GetTodoReq getTodoRes = todoProvider.retrieveTodo(day);
            return new BaseResponse<>(getTodoRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    @ResponseBody
    @GetMapping("/{userIdx}/X") // (GET) 127.0.0.1:9000/users/:userIdx
    public BaseResponse<GetUserFeedRes> getUserByIdx(@PathVariable("userIdx")int userIdx) {
        try{

            GetUserFeedRes getUsersRes = userProvider.retrieveUserFeed(userIdx, userIdx);
            return new BaseResponse<>(getUsersRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    // 회원 가입
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostUserRes> createUser(@RequestBody PostUserReq postUserReq) {
        if(postUserReq.getEmail() == null){
            return new BaseResponse<>(POST_USERS_EMPTY_EMAIL);
        }
        if(postUserReq.getPwd() == null){
            return new BaseResponse<>(POST_USERS_EMPTY_PASSWORD);
        }
        if(postUserReq.getName() == null){
            return new BaseResponse<>(POST_USERS_EMPTY_NAME);
        }

        if(postUserReq.getBirth() == null){
            return new BaseResponse<>(POST_USERS_EMPTY_BIRTHDAY);
        }
        if(postUserReq.getNickName() == null){
            return new BaseResponse<>(POST_USERS_EMPTY_NICKNAME);
        }

        // 정규 표현
        if(!isRegexEmail(postUserReq.getEmail())){
            return new BaseResponse<>(POST_USERS_INVALID_EMAIL);
        }

        try{
            PostUserRes postUserRes = userService.createUser(postUserReq);
            return new BaseResponse<>(postUserRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}