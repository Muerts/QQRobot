package com.example.qqrobot.entity;

import lombok.Data;

//"sender":{"age":0,"area":"","card":"英雄是俺。","level":"",
//        "nickname":"幕落人孤寂","role":"owner","sex":"unknown","title":"","user_id":3307211295}
@Data
public class sender {
    private Integer age;
    private String card;
    private String nickname;
    private String owner;
    private String user_id;
    private String remark;
}
