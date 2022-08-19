package com.example.demo.src.todoNew;

import com.example.demo.src.todoNew.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

@Repository
public class TodoNewDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //조회
    public List<TodoListRes> selectTodo(int userIdx, Date date){
        String selectTodoQuery = "SELECT todoName \n" +
                "FROM todoNew \n" +
                "WHERE todoNew.userIdx = ? and todoDate = ? \n";
        //"LIMIT 1";
        Object[] selectTodoParam = new Object[]{userIdx, date};
        return this.jdbcTemplate.query(selectTodoQuery,
                (rs, rowNum) -> new TodoListRes(
                        rs.getString("todoName")
                ), selectTodoParam);
    }
}
