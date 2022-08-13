package com.example.demo.src.inquire;

import com.example.demo.config.BaseException;
import com.example.demo.src.todo.TodoDao;
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
public class InquireProvider {

    private final InquireDao inquireDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public InquireProvider(InquireDao inquireDao, JwtService jwtService) {
        this.inquireDao = inquireDao;
        this.jwtService = jwtService;
    }

}
