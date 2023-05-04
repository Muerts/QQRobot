package com.example.qqrobot.function;


import com.example.qqrobot.entity.Message;
import com.example.qqrobot.tool.pretreatment;
import jakarta.annotation.PostConstruct;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;


@Component
public class PrivateMessage {
    @Autowired
    RedisTemplate redisTemplate;
    static PrivateMessage privateMessage;
    static String talk = null;
    static String HostQQ = "3307211295";
    static String girlQQ = "3168352498";
    @PostConstruct
    public void init(){
        privateMessage = this;
        privateMessage.redisTemplate = this.redisTemplate;
    }
   public static void private_talk(@NotNull Message message) throws Exception {
       String test = message.getRaw_message();
       int length = test.length();
       String sign = null;
       if(message.getUser_id().contains(HostQQ)) {
           if (test.contains("/同意")&&length<10) sign = "001";
           else if (test.contains("/拒绝")&&length<10) sign = "002";
           else if (test.contains("/好友列表")&&length<10) sign = "003";
           else if (test.contains("/删除好友")&&length<20) sign = "004";
           else if (test.contains("/开启")&&test.contains("语音")) sign = "005";
           else if (test.contains("/关闭")&&test.contains("语音")) sign = "006";
           else if (test.contains("/日志")) sign = "008";
           else if (test.contains("小时")||test.contains("分钟")||test.contains("秒")&&length<10) sign = "007";
           else sign = "000";
           switch (sign) {
               case "001":friendRequest.SuccessOrFail((String)privateMessage.redisTemplate.boundValueOps("FRI").get(),true);break;
               case "002":friendRequest.SuccessOrFail((String)privateMessage.redisTemplate.boundValueOps("FRI").get(),false);break;
               case "003":friendRequest.getFriendList();break;
               case "004":friendRequest.deleteFriend(pretreatment.getNumberFromString(message.getRaw_message()));break;
               case "005":voiceChat.open();break;
               case "006":voiceChat.close();break;
               case "007":ToHostAndOtherTalk.toTalkWithQQ(HostQQ,"女儿已为您定好闹钟");timeTalk.alarmClock(test);break;
               case "008":saveFile.sort(message.getMessage());break;
               default:  talk =  "在呢！\n爸爸，有什么事情吗？";
                   ToHostAndOtherTalk.toTalkWithQQ(message.getUser_id(),talk);
           }
       }else {
           int i = 0;
           if (!message.getUser_id().contains("3307211295"))
//           talk = "找我干嘛，扰我清梦！";
           {
               Random random = new Random();
               i = random.nextInt(222);
           }
           CQ_CodeTalk.comment(true, "face", i + "", message.getUser_id(),null);
       }

    }

}
