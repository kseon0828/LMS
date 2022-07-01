package com.example.demo.src.auth;


import com.example.demo.config.BaseException;
import com.example.demo.src.auth.model.PostLoginReq;
import com.example.demo.src.auth.model.PostLoginRes;
import com.example.demo.src.auth.model.User;
import com.example.demo.src.user.UserDao;
import com.example.demo.src.user.UserProvider;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service
public class AuthService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AuthDao authDao;
    private final AuthProvider authProvider;
    private final JwtService jwtService;


    @Autowired
    public AuthService(AuthDao authDao, AuthProvider authProvider, JwtService jwtService) {
        this.authDao = authDao;
        this.authProvider = authProvider;
        this.jwtService = jwtService;

    }

    public PostLoginRes logIn(PostLoginReq postLoginReq) throws BaseException {
        User user = authDao.getPwd(postLoginReq);
        String encryptPwd;

        try{
            //비밀번호 암호화
            encryptPwd = new SHA256().encrypt(postLoginReq.getPwd());
        }
        catch (Exception exception) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }

        //user에서 받아온 비밀번호와 암호화해준 비밀번호 비교
        if(user.getPwd().equals(encryptPwd)){
            int userIdx = user.getUserIdx();

            //이상이 없다면 jwt 발급
            String jwt = jwtService.createJwt(userIdx);
            return new PostLoginRes(userIdx, jwt);
        }else
            throw new BaseException(FAILED_TO_LOGIN);
    }

}
