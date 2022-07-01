package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostUserReq {
    private String email;
    private String name;
    private String pwd;
    private String birth;
    private String nickName;

}
