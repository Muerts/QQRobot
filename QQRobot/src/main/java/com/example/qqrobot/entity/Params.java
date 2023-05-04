package com.example.qqrobot.entity;

import lombok.Data;

@Data
public class Params {
    private String message_type;
    private int time;
    private String self_id;
    private int duration;
    private String type;
    private data data;
    private String operator_id;
    private String post_type;
    private String notice_type;
    private String user_id;
    private String group_id;
    private String message;
    private boolean auto_escape;
    private boolean approve;
    private String remark;
    private String flag;
    private String message_id;
}
