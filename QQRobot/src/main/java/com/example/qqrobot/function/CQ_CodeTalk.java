package com.example.qqrobot.function;

import com.alibaba.fastjson.JSONObject;
import com.example.qqrobot.Model.QQClient;
import com.example.qqrobot.entity.Request;
import com.example.qqrobot.entity.data;
import com.example.qqrobot.entityf.Message;
import com.example.qqrobot.entityf.Params;

import java.util.Random;

public class CQ_CodeTalk {
   static Params params =  new Params();
   static Request<Params> request =  new Request<Params>();
   static Message message = new Message();
   static data data = new data();
   static Random random = new Random();
 static public void SendCqCode_Private(String QQ){

      request.setAction("send_msg");
       params.setMessage_type("private");
       message.setType("face");
       data.setId("1");
       message.setData(data);
       params.setMessage(message);
       params.setUser_id(QQ);
       params.setAuto_escape(true);
       request.setParams(params);
     System.out.println(JSONObject.toJSONString(request));
       QQClient.sendMessage(JSONObject.toJSONString(request));
   }
   static void shacke_Group(){


   }
   static void comment(Boolean Private,String CQType,String CQData,String QQ,String AimQQ){
     if(Private)
       request.setAction("send_msg");
     else
         request.setAction("send_group_msg");
     if (Private)
       params.setMessage_type("private");
       message.setType(CQType);
       switch (CQType){
           case "text":data.setText(CQData);break;
           case "at":data.setQq(CQData);break;
           case "face":data.setId(CQData);break;
           case "poke":data.setQq(CQData);break;
           case "tts":data.setText(CQData);break;
           case "record":data.setFile(CQData);break;
           case "gift":data.setQq(AimQQ);data.setId(random.nextInt(13)+"");
           case "shake":break;
       }
       message.setData(data);
       params.setMessage(message);
       if (Private)
       params.setUser_id(QQ);
       else
           params.setGroup_id(QQ);
       request.setParams(params);
       System.out.println(JSONObject.toJSONString(request));
       QQClient.sendMessage(JSONObject.toJSONString(request));
   }
}
