package com.example.qqrobot.function;

import com.alibaba.fastjson.JSONObject;
import com.example.qqrobot.Model.QQClient;
import com.example.qqrobot.entity.Message;
import com.example.qqrobot.entity.Params;
import com.example.qqrobot.entity.Request;
import jakarta.annotation.PostConstruct;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


//此类得作用是实现消息发送
@Component
public class ToHostAndOtherTalk {
    static ToHostAndOtherTalk toHostAndOtherTalk;
    @Autowired
    static Params params = new Params();
    @Autowired
    RedisTemplate redisTemplate;
    static Request<Params> request = new Request<>();
    static String HostQQ = "3307211295";
    static String girlQQ = "3168352498";
    static String OPEN_OR_CLOSE = "false";
    static final String HOST_WILL = "voice";
    @PostConstruct
    public void init(){
        toHostAndOtherTalk = this;
        toHostAndOtherTalk.redisTemplate = this.redisTemplate;
    }
    public static void toTalkWithQQ(String QQ,String talk){
        OPEN_OR_CLOSE = (String)toHostAndOtherTalk.redisTemplate.opsForValue().get(HOST_WILL);
        if(OPEN_OR_CLOSE.equals("true")){
            CQ_CodeTalk.comment(true,"tts",talk,QQ,null);
        }
            else{
            request.setAction("send_msg");
            params.setMessage_type("private");
            params.setMessage(talk);
            params.setUser_id(QQ);
            params.setAuto_escape(true);
            request.setParams(params);
            QQClient.sendMessage(JSONObject.toJSONString(request));
        }
    }

    public static void toTalkWithGroupId(String groupId,String talk){
        OPEN_OR_CLOSE = (String)toHostAndOtherTalk.redisTemplate.opsForValue().get(HOST_WILL);
        if(OPEN_OR_CLOSE.equals("true")){
            CQ_CodeTalk.comment(false,"tts",talk,groupId,null);
        }else {
            request.setAction("send_group_msg");
            params.setMessage_type("group");
            params.setGroup_id(groupId);
            params.setMessage(talk);
            params.setAuto_escape(true);
            request.setParams(params);
            QQClient.sendMessage(JSONObject.toJSONString(request));
        }
    }
    public static void deleteMessage(@NotNull Message message){
        request.setAction("delete_msg");
        params.setMessage_type("group");
        params.setGroup_id(message.getGroup_id());
        params.setMessage_id(message.getMessage_id());
        request.setParams(params);
        QQClient.sendMessage(JSONObject.toJSONString(request));
    }
    public static void  BanSendMessage(@NotNull Message message){
        request.setAction("set_group_ban");
        params.setDuration(30*60);
        params.setGroup_id(message.getGroup_id());
        params.setUser_id(message.getUser_id());
        request.setParams(params);
        QQClient.sendMessage(JSONObject.toJSONString(request));
    }


}
