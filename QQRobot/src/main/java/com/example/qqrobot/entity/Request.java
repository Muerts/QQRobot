package com.example.qqrobot.entity;

import lombok.Data;

import java.util.List;

@Data
public class Request<T>{
    private String action;
    private T params;
    private String echo;
    private List<T> paramss;
}
