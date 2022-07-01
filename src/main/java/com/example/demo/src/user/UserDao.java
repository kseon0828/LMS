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

    public GetUserInfoRes selectUserInfo(int userIdx){
        String selectUserInfoQuery = "SELECT u.userIdx as userIdx,\n" +
                "            u.nickName as nickName,\n" +
                "            u.name as name,\n" +
                "            u.profileImgUrl as profileImgUrl,\n" +
                "            u.website as website,\n" +
                "            u.introduction as introduction,\n" +
                "            IF(followerCount is null, 0, followerCount) as followerCount,\n" +
                "            If(followingCount is null, 0, followingCount) as followingCount,\n" +
                "            count(p.postIdx) as postCount\n" +
                "        FROM User as u\n" +
                "            join Post as p on p.userIdx = u.userIdx and p.status = 'ACTIVE'\n" +
                "            left join (select followerIdx, count(followIdx) as followerCount from Follow WHERE status = 'ACTIVE' group by followIdx) fc on fc.followerIdx = u.userIdx\n" +
                "            left join (select followeeIdx, count(followIdx) as followingCount from Follow WHERE status = 'ACTIVE' group by followIdx) f on f.followeeIdx = u.userIdx\n" +
                "        WHERE u.userIdx = ? and u.status = 'ACTIVE'\n" +
                "        group by u.userIdx;";
        int selectUserInfoParam = userIdx;
        return this.jdbcTemplate.queryForObject(selectUserInfoQuery,
                (rs,rowNum) -> new GetUserInfoRes(
                        rs.getInt("userIdx"),
                        rs.getString("nickName"),
                        rs.getString("name"),
                        rs.getString("profileImgUrl"),
                        rs.getString("website"),
                        rs.getString("introduction"),
                        rs.getInt("followerCount"),
                        rs.getInt("followingCount"),
                        rs.getInt("postCount")
                ),selectUserInfoParam);

    }

    public List<GetUserPostsRes> selectUserPosts(int userIdx){
        String selectUserPostsQuery = "\n" +
                "        SELECT p.postIdx as postIdx,\n" +
                "            pi.imgUrl as postImgUrl\n" +
                "        FROM Post as p\n" +
                "            join PostImgUrl as pi on pi.postIdx = p.postIdx and pi.status = 'ACTIVE'\n" +
                "            join User as u on u.userIdx = p.userIdx\n" +
                "        WHERE p.status = 'ACTIVE' and u.userIdx = ?\n" +
                "        group by p.postIdx\n" +
                "        HAVING min(pi.postImgUrlIdx)\n" +
                "        order by p.postIdx; " ;
        int selectUserPostsParam = userIdx;
        return this.jdbcTemplate.query(selectUserPostsQuery,
                (rs,rowNum) -> new GetUserPostsRes(
                        rs.getInt("postIdx"),
                        rs.getString("postImgUrl")
                ), selectUserPostsParam);
    }

    public int checkUserExist(int userIdx){
        String checkUserExistQuery = "select exists(select userIdx from User where userIdx = ?)";
        int checkUserExistParams = userIdx;
        return this.jdbcTemplate.queryForObject(checkUserExistQuery,
                int.class,
                checkUserExistParams);

    }

    // 회원 가입
    public int insertUser(PostUserReq postUserReq){
        String insertUserQuery = "insert into User (name,nickName, email, pwd,birth) VALUES (?,?,?,?,?)";
        Object[] insertUserParams = new Object[]{postUserReq.getName(), postUserReq.getNickName(), postUserReq.getEmail(),postUserReq.getPwd(), postUserReq.getBirth()};
        this.jdbcTemplate.update(insertUserQuery, insertUserParams);

        String lastInserIdQuery = "select last_insert_id()";

        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }


    // 이메일 확인
    public int checkEmailExist(String email){
        String checkEmailQuery = "select exists(select email from User where email = ?)";
        String checkEmailParams = email;
        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams);

    }

}
