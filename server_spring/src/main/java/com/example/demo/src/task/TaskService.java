package com.example.demo.src.task;

import com.example.demo.config.BaseException;
import com.example.demo.src.task.model.PostTaskReq;
import com.example.demo.src.task.model.PostTaskRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service
public class TaskService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TaskDao taskDao;
    private final TaskProvider taskProvider;
    private final JwtService jwtService;


    @Autowired
    public TaskService(TaskDao taskDao, TaskProvider taskProvider, JwtService jwtService) {
        this.taskDao = taskDao;
        this.taskProvider = taskProvider;
        this.jwtService = jwtService;

    }

    // 과제 생성
    public PostTaskRes createTask(int userIdx, String className, Date sDate, Date eDate, Date eTime, String postTaskReq) throws BaseException {

        try{
            int taskIdx = taskDao.insertTask(userIdx, className, sDate, eDate, eTime, postTaskReq);
            return new PostTaskRes(taskIdx);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 과제 수정
    public void modifyTask(int userIdx, int taskIdx, Date sDate, Date eDate, Date eTime, PostTaskReq postTaskReq) throws BaseException {

        try{
            //taskDao가 잘 실행되면 1, 아니면 0을 전달 받아 error 코드 표시
            int result = taskDao.updateTask(userIdx, taskIdx, sDate, eDate, eTime, postTaskReq.getTaskName());

            if(result == 0){
                throw new BaseException(MODIFY_FAIL_POST);
            }
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //과제 삭제
    public void deleteTask(int taskIdx) throws BaseException{
        try{
            //taskDao가 잘 실행되면 1, 아니면 0을 전달 받아 error 코드 표시
            int result = taskDao.deleteTask(taskIdx);

            if(result == 0){
                throw new BaseException(DELETE_FAIL_POST);
            }
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
