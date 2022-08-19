package com.example.demo.src.inquire;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.inquire.model.PostInquireReq;
import com.example.demo.src.inquire.model.PostInquireRes;
import com.example.demo.src.todo.TodoProvider;
import com.example.demo.src.todo.TodoService;
import com.example.demo.src.todo.model.GetTodoListRes;
import com.example.demo.src.todo.model.PostTodoReq;
import com.example.demo.src.todo.model.PostTodoRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/inquire")
public class InquireController {

    private final InquireProvider inquireProvider;
    private final InquireService inquireService;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public InquireController(InquireProvider inquireProvider, InquireService inquireService, JwtService jwtService) {
        this.inquireProvider = inquireProvider;
        this.inquireService = inquireService;
        this.jwtService = jwtService;
    }

    //문의 생성하기
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostInquireRes> getUserByIdx(@RequestBody PostInquireReq postInquireReq) {

        try{
            PostInquireRes postInquireRes = inquireService.createInquire(postInquireReq);

            /*
            RestTemplate restTemplate = new RestTemplate();

            Map<String,Object> request = new HashMap<String,Object>();
            request.put("username", " 문의자 : "+postInquireReq.getUserName());

            String content = postInquireReq.getUserName()+'\n'+postInquireReq.getUserEmail()+'\n'+postInquireReq.getIntro()+'\n'+postInquireReq.getContent();

            request.put("text", content);

            HttpEntity<Map<String,Object>> entity = new HttpEntity<Map<String,Object>>(request);

            String url = "..."; // 사용할 슬랙의 Webhook URL 넣기

            restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
             */

            return new BaseResponse<>(postInquireRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}