package com.example.demo.src.task;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.task.model.GetTaskListRes;
import com.example.demo.src.task.model.PostTaskReq;
import com.example.demo.src.task.model.PostTaskRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskProvider taskProvider;
    private final TaskService taskService;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public TaskController(TaskProvider taskProvider, TaskService taskService, JwtService jwtService) {
        this.taskProvider = taskProvider;
        this.taskService = taskService;
        this.jwtService = jwtService;
    }

    //날짜 과제 조회하기
    @ResponseBody
    @GetMapping("/{date}")
    public BaseResponse<GetTaskListRes> getTaskList(@PathVariable("date") String date) {

        try{
            SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy-MM-dd");
            // String 타입을 Date 타입으로 변환
            Date formatDate = dtFormat.parse(date);

            int userIdxByJwt = jwtService.getUserIdx();
            System.out.println();

            GetTaskListRes getTaskRes = taskProvider.retrieveTask(userIdxByJwt, formatDate);
            return new BaseResponse<>(getTaskRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    //과제 생성하기
    @ResponseBody
    @PostMapping("/{date}")
    public BaseResponse<PostTaskRes> getUserByIdx(@PathVariable("startDate") String startDate, @RequestParam("taskName") String postTaskReq, @RequestParam("className") String className, @RequestParam("endDate") String endDate, @RequestParam("endTime") String endTime) {

        try{
            SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
            // String 타입을 Date 타입으로 변환
            Date sDate = dtFormat.parse(startDate);
            Date eDate = dtFormat.parse(endDate);

            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            Date eTime = timeFormat.parse(endTime);

            int userIdxByJwt = jwtService.getUserIdx();


            PostTaskRes postTaskRes = taskService.createTask(userIdxByJwt, className, sDate, eDate, eTime, postTaskReq);
            return new BaseResponse<>(postTaskRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    //과제 수정하기
    @ResponseBody
    @PatchMapping("/{taksIdx}")
    public BaseResponse<String> modifyTask(@PathVariable("taskIdx") int taskIdx, @RequestParam("taskName") PostTaskReq postTaskReq, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, @RequestParam("endTime") String endTime) {
        try{
            SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
            // String 타입을 Date 타입으로 변환
            Date sDate = dtFormat.parse(startDate);
            Date eDate = dtFormat.parse(endDate);

            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            Date eTime = timeFormat.parse(endTime);

            int userIdxByJwt = jwtService.getUserIdx();

            taskService.modifyTask(userIdxByJwt, taskIdx, sDate, eDate, eTime, postTaskReq);
            String result = " 과제 수정을 완료하였습니다.";
            return new BaseResponse<>(result);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    //과제 삭제
    @ResponseBody
    @PatchMapping("/{taskIdx}/status")
    public BaseResponse<String> deleteTask(@PathVariable ("taskIdx") int taskIdx) {
        try{
            taskService.deleteTask(taskIdx);
            String result = "과제 삭제를 성공했습니다.";
            return new BaseResponse<>(result);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}