package com.example.qqrobot.thread;

import com.example.qqrobot.function.timeTalk;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockTask implements Runnable{

    @Override
    public void run() {
        while (true){
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            System.out.println( format.format(date));

//            if( format.format(date).equals("15：00"))
//                timeTalk.ontime();
            if(format.format(date).equals("13:26")) {
                timeTalk.ontime2();
                break;
            }
                try {
                    Thread.sleep(50*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
            }
        }
    }

    public static void ontime(){
        new Thread(new ClockTask()).start();
    }
}
