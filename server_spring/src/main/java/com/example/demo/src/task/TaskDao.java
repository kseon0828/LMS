package com.example.demo.src.task;

import com.example.demo.src.task.model.GetTaskRes;
import com.example.demo.src.todo.model.GetTodoRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

@Repository
public class TaskDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //조회
    public List<GetTaskRes> selectTask(int userIdx, Date date){
        String selectTaskQuery = "SELECT complete, taskName, endDate, endTime \n" +
                "FROM task \n" +
                "WHERE task.userIdx = ? and ? between task.startDate ands task.endDate";
        Object[] selectTaskParam = new Object[]{userIdx, date};
        return this.jdbcTemplate.query(selectTaskQuery,
                (rs, rowNum) -> new GetTaskRes(
                        rs.getInt("complete"),
                        rs.getString("taskName"),
                        rs.getString("endDate"),
                        rs.getString("endTime")
                ), selectTaskParam);
    }

    //생성
    public int insertTask(int userIdx, String className, Date sDate, Date eDate, Date eTime, String taskName){

        String checkClassQuery = "select exists(select className from class where className = ?)";
        String checkClassParams = className;
        int check =  this.jdbcTemplate.queryForObject(checkClassQuery, int.class, checkClassParams);


        if(check == 0) {
            String selectUnivIdQuery = "select univIdx from user where userIdx = ?";
            int selectUnivIdParams= userIdx;
            int univIdx = this.jdbcTemplate.queryForObject(selectUnivIdQuery, Integer.class, selectUnivIdParams);


            String insertClassQuery = "insert into class (univIdx, className) VALUES (?, ?)";
            Object[] insertClassParams = new Object[]{univIdx, checkClassParams};
            this.jdbcTemplate.update(insertClassQuery, insertClassParams);
        }

        String selectClassQuery = "select classIdx from class where className = ?";
        String selectClassParams= className;
        int classIdx = this.jdbcTemplate.queryForObject(selectClassQuery, Integer.class, selectClassParams);


        String insertTaskQuery = "INSERT into task(userIdx, classIdx, startDate, endDate, endTime, complete, taskName) \n" +
                " VALUES(?, ?, ?, ?, ?, 0, ?)";
        Object[] insertTaskParams = new Object[]{userIdx, classIdx, sDate, eDate, eTime, taskName};
        this.jdbcTemplate.update(insertTaskQuery, insertTaskParams);

        String lastInsertIdQuery = "select last_insert_id()";

        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }

    //수정
    public int updateTask(int userIdx, int taskIdx, Date sDate, Date eDate, Date eTime, String taskName){
        String updateQuery = "UPDATE task SET taskName = ?, startDate = ?, endDate = ?, endTime = ? \n" +
                " WHERE userIdx = ? and taskIdx = ?";
        Object[] updateParams = new Object[]{taskName, sDate, eDate, eTime, userIdx, taskIdx};

        return this.jdbcTemplate.update(updateQuery,updateParams);
    }

    //삭제
    public int deleteTask (int taskIdx){
        String deleteQuery = "UPDATE task SET status = 'INACTIVE' WHERE taskIdx = ?";
        Object[] deleteParams = new Object[] {taskIdx};

        return this.jdbcTemplate.update(deleteQuery,deleteParams);
    }
}
