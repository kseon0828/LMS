package com.example.demo.src.user;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/users")
public class UserController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final UserProvider userProvider;
    @Autowired
    private final UserService userService;
    @Autowired
    private final JwtService jwtService;




    public UserController(UserProvider userProvider, UserService userService, JwtService jwtService){
        this.userProvider = userProvider;
        this.userService = userService;
        this.jwtService = jwtService;
    }



    @ResponseBody
    @GetMapping("/{userIdx}")
    public BaseResponse<GetUserFeedRes> getUserFeed(@PathVariable("userIdx")int userIdx) {
        try{
            GetUserFeedRes getUserFeedRes = userProvider.retrieveUserFeed(userIdx, userIdx);
            return new BaseResponse<>(getUserFeedRes);
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