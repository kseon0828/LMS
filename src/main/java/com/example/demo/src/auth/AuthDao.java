package com.example.demo.src.auth;


import com.example.demo.src.auth.model.PostLoginReq;
import com.example.demo.src.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class AuthDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public User getPwd(PostLoginReq postLoginReq){
        String getPwdQuery = "select userIdx, univIdx, userName,userSsn, userEmail, password from user where userEmail =?";
        String getPwdParams = postLoginReq.getEmail();

        return this.jdbcTemplate.queryForObject(getPwdQuery,
                (rs, rowNum) -> new User(
                        rs.getInt("userIdx"),
                        rs.getInt("univIdx"),
                        rs.getString("userName"),
                        rs.getString("userSsn"),
                        rs.getString("userEmail"),
                        rs.getString("password")
                ),
                getPwdParams);
    }


}
