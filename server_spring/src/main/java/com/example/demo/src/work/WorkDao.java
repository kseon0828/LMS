package com.example.demo.src.work;

import com.example.demo.src.task.model.GetTaskRes;
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
    public List<GetTaskRes> selectTask(int userIdx, Date date){
        String selectTaskQuery = "SELECT complete, taskName, endDate, endTime \n" +
                "FROM task \n" +
                "WHERE task.userIdx = ? and ? between task.startDate and task.endDate";
        Object[] selectTaskParam = new Object[]{userIdx, date};
        return this.jdbcTemplate.query(selectTaskQuery,
                (rs, rowNum) -> new GetTaskRes(
                        rs.getInt("complete"),
                        rs.getString("taskName"),
                        rs.getString("endDate"),
                        rs.getString("endTime")
                ), selectTaskParam);
    }

}
