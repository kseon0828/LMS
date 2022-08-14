package com.example.demo.src.inquire.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostInquireReq {
    private String userName;
    private String userEmail;
    private String intro;
    private String content;
}
