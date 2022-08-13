package com.example.demo.src.inquire.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostInquireReq {
    private String userEmail;
    private String title;
    private String content;
}
