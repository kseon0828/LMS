package com.example.demo.src.work;

import com.example.demo.src.task.model.GetTaskRes;
import com.example.demo.src.work.model.GetWorkRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

@Repository
public class WorkDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //조회
    public List<GetWorkRes> selectWork(int userIdx, Date date){
        String selectWorkQuery = "SELECT complete, workName, endDate, endTime \n" +
                "FROM work \n" +
                "WHERE work.userIdx = ? and ? between work.startDate and work.endDate";
        Object[] selectWorkParam = new Object[]{userIdx, date};
        return this.jdbcTemplate.query(selectWorkQuery,
                (rs, rowNum) -> new GetWorkRes(
                        rs.getInt("complete"),
                        rs.getString("workName"),
                        rs.getString("endDate"),
                        rs.getString("endTime")
                ), selectWorkParam);
    }

}
