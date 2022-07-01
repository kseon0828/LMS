package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchUserReq {
    private String name;
    private String nickName;
    private String website;
    private String introduction;
    private String profileImgUrl;
}
