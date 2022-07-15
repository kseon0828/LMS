package com.example.demo.src.todo;

import com.example.demo.src.todo.model.GetTodoRes;
import com.example.demo.src.user.model.PostUserReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

@Repository
public class TodoDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //조회
    public List<GetTodoRes> selectTodo(int userIdx, Date date){
        String selectTodoQuery = "SELECT complete, todoName \n" +
                "FROM todoList \n" +
                "WHERE todoList.userIdx = ? and todoList.date = ? ";
        Object[] selectTodoParam = new Object[]{userIdx, date};
        return this.jdbcTemplate.query(selectTodoQuery,
                (rs, rowNum) -> new GetTodoRes(
                        rs.getInt("complete"),
                        rs.getString("todoName")
                ), selectTodoParam);
    }


    //생성
    public int insertTodo(int userIdx, Date date, String todoName){
        String insertTodoQuery = "INSERT into todoList(userIdx, date, complete, todoName) VALUES(?, ?, 0, ?)";
        Object[] insertTodoParams = new Object[]{userIdx, date, todoName};
        this.jdbcTemplate.update(insertTodoQuery, insertTodoParams);

        String lastInsertIdQuery = "select last_insert_id()";

        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }

    //수정
    public int updateTodo(int userIdx, int todoIdx, String todoName){
        String updateQuery = "UPDATE todoList SET todoName = ? WHERE userIdx = ? and todoIdx = ?" ;
        Object[] updateParams = new Object[]{todoName, userIdx, todoIdx};

        return this.jdbcTemplate.update(updateQuery,updateParams);
    }

    //삭제
    public int deleteTodo (int todoIdx){
        String deleteQuery = "UPDATE todoList SET status = 'INACTIVE' WHERE todoIdx = ?";
        Object[] deleteParams = new Object[] {todoIdx};

        return this.jdbcTemplate.update(deleteQuery,deleteParams);
    }
}
