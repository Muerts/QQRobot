package com.example.qqrobot.function;

import com.alibaba.fastjson.JSONObject;
import com.example.qqrobot.Model.QQClient;
import com.example.qqrobot.entity.Params;
import com.example.qqrobot.entity.Request;
import jakarta.annotation.Resource;
//此类是处理各种请求
public class ToDealWithRequest {
    @Resource
    static Params params = new Params();
    static Request<Params> request = new Request<>();
   static public void AgreeOrDisagreeRequest(String flag,Boolean sign){
       request.setAction("set_friend_add_request");
       params.setApprove(sign);
       params.setFlag(flag);
       params.setAuto_escape(true);
       request.setParams(params);
       QQClient.sendMessage(JSONObject.toJSONString(request));
    }
}
