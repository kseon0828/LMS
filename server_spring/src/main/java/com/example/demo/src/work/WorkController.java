package com.example.demo.src.work;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.task.model.GetTaskListRes;
import com.example.demo.src.work.model.GetWorkListRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/work")
public class WorkController {
    private final WorkProvider workProvider;
    private final WorkService workService;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public WorkController(WorkProvider workProvider, WorkService workService, JwtService jwtService) {
        this.workProvider = workProvider;
        this.workService = workService;
        this.jwtService = jwtService;
    }

    //날짜 과제 조회하기
    @ResponseBody
    @GetMapping("/{date}")
    public BaseResponse<GetTaskListRes> getWorkList(@PathVariable("date") String date) {

        try{
            SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy-MM-dd");
            // String 타입을 Date 타입으로 변환
            Date formatDate = dtFormat.parse(date);

            int userIdxByJwt = jwtService.getUserIdx();
            System.out.println();

            GetTaskListRes getWorkRes = workProvider.retrieveTask(userIdxByJwt, formatDate);
            return new BaseResponse<>(getWorkRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}