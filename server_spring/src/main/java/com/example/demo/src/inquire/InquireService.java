package com.example.demo.src.inquire;

import com.example.demo.config.BaseException;
import com.example.demo.src.inquire.model.PostInquireReq;
import com.example.demo.src.inquire.model.PostInquireRes;
import com.example.demo.src.todo.TodoDao;
import com.example.demo.src.todo.TodoProvider;
import com.example.demo.src.todo.model.PostTodoReq;
import com.example.demo.src.todo.model.PostTodoRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class InquireService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final InquireDao inquireDao;
    private final InquireProvider inquireProvider;


    @Autowired
    public InquireService(InquireDao inquireDao, InquireProvider inquireProvider, JwtService jwtService) {
        this.inquireDao = inquireDao;
        this.inquireProvider = inquireProvider;
    }

    // 문의 생성
    //public PostTodoRes createInquire(String userEmail, String title, String content) throws BaseException {
    public PostInquireRes createInquire(PostInquireReq postInquireReq) throws BaseException {

        //try{
            int inquireIdx = inquireDao.insertInquire(postInquireReq);
            return new PostInquireRes(inquireIdx);
        //} catch (Exception exception) {
        //    throw new BaseException(DATABASE_ERROR);
        //}
    }

}
