package com.example.qqrobot.Model;

import com.alibaba.fastjson.JSONObject;
import com.example.qqrobot.entity.Message;
import com.example.qqrobot.function.GroupMessage;
import com.example.qqrobot.function.PrivateMessage;
import com.example.qqrobot.function.friendRequest;
import com.example.qqrobot.thread.ReconnectTask;
import jakarta.websocket.*;

import java.io.IOException;
import java.net.URI;


@ClientEndpoint
public class QQClient {

    private Session session;
    private static QQClient INSTANCE;
    static String HostQQ = "3307211295";

    private QQClient(String url) throws DeploymentException, IOException {
        session = ContainerProvider.getWebSocketContainer().connectToServer(this, URI.create(url));
    }
    private volatile static boolean connecting = false;

    public synchronized static boolean connect(String url){
        try {
            INSTANCE = new QQClient(url);
            connecting = false;
            return  true;
        } catch (Exception e) {
            System.out.println("连接失败");
            return false;
        }
    }
    public synchronized static void reconnect(){
        if(!connecting){
            connecting = true;
            if(INSTANCE != null){
                INSTANCE.session = null;
                INSTANCE = null;
            }
            ReconnectTask.execute();
        }
    }
    //连接成功触发
    @OnOpen
    public void onopen(Session session){
        System.out.println("连接成功！");
       // ClockTask.ontime();
    }
    //收到消息
    @OnMessage
    public void onmessage(String json) throws Exception {
        System.out.println(json);
        Message message = JSONObject.parseObject(json, Message.class);
        if("message".equals(message.getPost_type())){
            if(message.getMessage_type().equals("private"))
                PrivateMessage.private_talk(message);//私信回复

            if(message.getMessage_type().equals("group"))
                GroupMessage.group_talk(message);//群聊回复
        }else
        if("request".equals(message.getPost_type())){

            friendRequest.make_Friend(message);
        }else
        if(message.getData() != null){
            if(message.getData().get(0).getNickname()!=null)
            friendRequest.sendFriendListToHost(message);
        }
    }

    //连接关闭
    @OnClose
    public void onclose(Session session){
        System.out.println("连接关闭！");
        reconnect();
    }
    //连接异常
    @OnError
    public void onerror(Session session,Throwable error){
        System.out.println("出现异常，连接失败！");
        System.out.println(error);
        reconnect();
    }
    public static void sendMessage(String json){
            QQClient.INSTANCE.session.getAsyncRemote().sendText(json);
    }

}
