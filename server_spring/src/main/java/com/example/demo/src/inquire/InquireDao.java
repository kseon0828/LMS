package com.example.demo.src.inquire;

import com.example.demo.src.inquire.model.PostInquireReq;
import com.example.demo.src.todo.model.GetTodoRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class InquireDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //생성
    public int insertInquire(PostInquireReq postInquireReq) {

        String insertInquireQuery = "INSERT into inquiry(userName, userEmail, intro, content) VALUES(?, ?, ?, ?)";
        Object[] insertInquireParams = new Object[]{postInquireReq.getUserName(), postInquireReq.getUserEmail(), postInquireReq.getIntro(), postInquireReq.getContent()};
        this.jdbcTemplate.update(insertInquireQuery, insertInquireParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }
}