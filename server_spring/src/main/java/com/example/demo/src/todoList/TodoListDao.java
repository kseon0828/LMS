package com.example.demo.src.todoList;

import com.example.demo.src.todo.model.GetTodoRes;
import com.example.demo.src.todoList.model.GetTodoListRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

@Repository
public class TodoListDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //조회
    public List<GetTodoListRes> selectTodoList(int userIdx, Date date){
        String selectTodoQuery = "SELECT complete, todoName, todoDate, todoTime \n" +
                "FROM todoList \n" +
                "WHERE todoList.userIdx = ? and todoDate = ? ";
        Object[] selectTodoParam = new Object[]{userIdx, date};
        return this.jdbcTemplate.query(selectTodoQuery,
                (rs, rowNum) -> new GetTodoListRes(
                        rs.getInt("complete"),
                        rs.getString("todoName"),
                        rs.getString("todoDate"),
                        rs.getString("todoTime")
                ), selectTodoParam);
    }
}
