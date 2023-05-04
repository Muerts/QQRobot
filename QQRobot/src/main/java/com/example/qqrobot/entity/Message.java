package com.example.qqrobot.entity;

import lombok.Data;

import java.util.List;

@Data
//[CQ:image,file=9f21cd318dba43a41a7c1d6675e72093.image,subType=7,url=https://gchat.qpic.cn/gchatpic_new/3455924379/822387278-2596608878-9F21CD318DBA43A41A7C1D6675E72093/0?term=2\u0026amp;is_origin=0]
public class Message {
    private List<sender> data;
    private String post_type;
    private String meta_event_type;
    private String message_type;
    private String message;
    private int time;
    private String self_id;
    private String sub_type;
    private String user_id;
    private String group_id;
    private String target_id;
    private String raw_message;
    private String flag;
    private sender sender;
    private String message_id;
    private Integer message_seq;
    private String anonymous;
    private String request_type;

}
