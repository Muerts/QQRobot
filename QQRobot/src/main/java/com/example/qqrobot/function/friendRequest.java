package com.example.qqrobot.function;

import com.alibaba.fastjson.JSONObject;
import com.example.qqrobot.Model.QQClient;
import com.example.qqrobot.entity.Message;
import com.example.qqrobot.entity.Params;
import com.example.qqrobot.entity.Request;
import com.example.qqrobot.entity.sender;
import jakarta.annotation.PostConstruct;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class friendRequest {

    @Autowired
    RedisTemplate redisTemplate;
    static friendRequest friendRequest;
    static Params params = new Params();
    static String  talk = "爸爸我的好友列表如下:\n";
    static String HostQQ = "3307211295";
    static String girlQQ = "3168352498";
    static Request<Params> request = new Request<>();

    public static void sendFriendListToHost(@NotNull Message message) {
        List<sender> data = message.getData();

        for (sender sender : data) {
            if (sender.getUser_id()!=null)
            talk = talk +"账户名:"+sender.getNickname()+",备注:"+sender.getRemark()+",QQ号:"+sender.getUser_id()+";\n";
        }
        ToHostAndOtherTalk.toTalkWithQQ(HostQQ,talk);
        talk = "爸爸我的好友列表如下:\n";
    }

    @PostConstruct
    public void init(){
        friendRequest = this;
        friendRequest.redisTemplate = this.redisTemplate;
    }

    public static void make_Friend(@NotNull Message message){
        //先验证数据库中是否有该用户的请求
        String flag =(String) friendRequest.redisTemplate.boundValueOps("FRI").get();
        System.out.println(flag);
        if (flag!=null&&flag.equals(message.getFlag())){
            friendRequest.redisTemplate.delete("FRI");
            return;
        }
         talk = "爸爸:\n" +
         "QQ:"+message.getUser_id()+"的账户添加我为好友，请问是否添加？(回答'同意'或'拒绝')";
        friendRequest.redisTemplate.boundValueOps("FRI").set(message.getFlag());
        ToHostAndOtherTalk.toTalkWithQQ(HostQQ,talk);
    }
    //是否添加好友
  public static void SuccessOrFail(@NotNull String flag, @NotNull Boolean sign){

         talk =  sign?"添加成功！":"已拒绝";
         ToDealWithRequest.AgreeOrDisagreeRequest(flag,sign);
         ToHostAndOtherTalk.toTalkWithQQ(HostQQ,talk);
  }
//获得好友列表
  public static void getFriendList() {
      request.setAction("get_friend_list");
      QQClient.sendMessage(JSONObject.toJSONString(request));
  }
  //删除好友
  public static void deleteFriend(String QQ){
        request.setAction("delete_friend");
        params.setUser_id(QQ);
        request.setParams(params);
        QQClient.sendMessage(JSONObject.toJSONString(request));
  }
}
