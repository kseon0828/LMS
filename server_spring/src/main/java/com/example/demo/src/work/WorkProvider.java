package com.example.demo.src.work;

import com.example.demo.config.BaseException;
import com.example.demo.src.task.model.GetTaskListRes;
import com.example.demo.src.task.model.GetTaskRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class WorkProvider {
    private final WorkDao workDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public WorkProvider(WorkDao workDao, JwtService jwtService) {
        this.workDao = workDao;
        this.jwtService = jwtService;
    }

    //task리스트 조회하기
    public GetTaskListRes retrieveTask(int userIdx, Date date) throws BaseException {

        try{
            List<GetTaskRes> getTaskRes = workDao.selectTask(userIdx, date);
            GetTaskListRes getTaskListRes =new GetTaskListRes(getTaskRes);
            return getTaskListRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}

