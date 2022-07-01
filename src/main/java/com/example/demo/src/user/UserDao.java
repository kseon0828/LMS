package com.example.demo.src.user;


import com.example.demo.src.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    // 회원 가입
    public int insertUser(PostUserReq postUserReq){
        String checkUnivQuery = "select exists(select univName from university where univName = ?)";
        String checkUnivParams = postUserReq.getUniv();
        int check =  this.jdbcTemplate.queryForObject(checkUnivQuery, int.class, checkUnivParams);

        if(check == 0) {
            String insertUnivQuery = "insert into university (univName) VALUES (?)";
            String insertUnivParams = checkUnivParams;
            this.jdbcTemplate.update(insertUnivQuery, insertUnivParams);
        }

        String selectUserUnivQuery = "select univIdx from university where univName = ?";
        String selectUserUnivParams=postUserReq.getUniv();
        int univIdx = this.jdbcTemplate.queryForObject(selectUserUnivQuery, Integer.class, selectUserUnivParams);

        String insertUserQuery = "insert into User (univIdx, userSsn, userName, userEmail, password) VALUES (?,?,?,?,?)";
        Object[] insertUserParams = new Object[]{univIdx, postUserReq.getSsn(), postUserReq.getName(), postUserReq.getEmail(),postUserReq.getPwd()};
        this.jdbcTemplate.update(insertUserQuery, insertUserParams);

        String lastInsertIdQuery = "select last_insert_id()";

        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }


    // 이메일 확인
    public int checkEmailExist(String email){
        String checkEmailQuery = "select exists(select userEmail from User where userEmail = ?)";
        String checkEmailParams = email;
        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams);

    }

}
