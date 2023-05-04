package com.example.qqrobot.function;

import com.example.qqrobot.tool.TimeAnalysis;

import java.util.Timer;
import java.util.TimerTask;

public class timeTalk {
    static String HostQQ = "3307211295";
    static String groupId = null;
    static String talk = null;

   public static void ontime(){
       talk = "为了我，你今天也得要好好学习啊";
       ToHostAndOtherTalk.toTalkWithQQ(HostQQ,talk);
   }
    public static void ontime2(){
        talk = "或许当你努力的千次万次，可仍未达到目标，不必沮丧。你不妨回头看看，你用过力，留下的汗，都会在来时路上永不消散。这些东西伴随着你的成长，象征着你的生长。未至目标，但你终会强大，因为你未至的目标向来高大。";
        CQ_CodeTalk.comment(true,"tts",talk,HostQQ,null);
    }


    public static void alarmClock(String time) {
        int i = TimeAnalysis.totalTime(time);
        System.out.println("时间:"+i);
         Timer timer = new Timer();
         TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                ToHostAndOtherTalk.toTalkWithQQ(HostQQ,"爸爸~时间到了");
            }
        };
        timer.schedule(timerTask,i);
    }

}
