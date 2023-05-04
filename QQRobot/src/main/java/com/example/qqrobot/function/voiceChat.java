package com.example.qqrobot.function;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class voiceChat {
    static final String HOST_WILL = "voice";
    static voiceChat voiceChat;
    static String HostQQ = "3307211295";
   @Autowired
    RedisTemplate redisTemplate;
    @PostConstruct
    public void init(){
        voiceChat = this;
        voiceChat.redisTemplate = this.redisTemplate;
    }
    public static void open(){
       voiceChat.redisTemplate.opsForValue().set(HOST_WILL,"true");
        CQ_CodeTalk.comment(true,"tts","语音已开启",HostQQ,null);
    }
    public static void close(){
        voiceChat.redisTemplate.opsForValue().set(HOST_WILL,"false");
        ToHostAndOtherTalk.toTalkWithQQ(HostQQ,"语音已关闭");
    }
}
