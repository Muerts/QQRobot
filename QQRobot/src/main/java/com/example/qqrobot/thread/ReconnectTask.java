package com.example.qqrobot.thread;

import com.example.qqrobot.Model.QQClient;

public class ReconnectTask implements Runnable{

    @Override
    public void run() {
        while (true){
            if(QQClient.connect("ws://127.0.0.1:9090")){
                break;
            }else {
                try {
                    Thread.sleep(3*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void execute(){
        new Thread(new ReconnectTask()).start();
    }
}
