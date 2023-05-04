package com.example.qqrobot.tool;

public class TimeAnalysis {
    public static Integer hour = 60*1000*60;
    public static Integer minutes = 60*1000;
    public static Integer seconds = 1000;
    public static String CN = "零一二三四五六七八九十";

    public static int totalTime(String time) {
        int num = 0;
        //System.out.println(CN.charAt(0));
       // String time = "一百三十二秒";
        if(time!=null&&time.contains("小时")){
            String[] s = time.split("小时");
            System.out.println(s[0]);
            num = num + split(s[0])*hour;
            if (s.length>1)
                time = s[1];
            else
                time = null;

        }
        if(time!=null&&time.contains("分钟")){
            String[] s = time.split("分钟");
            System.out.println(s[0]);
            num = num + split(s[0])*minutes;
            if (s.length>1)
                time = s[1];
            else
                time = null;
        }
        if(time!=null&&time.contains("秒")){
            String[] s = time.split("秒");
            System.out.println(s[0]);
            num = num + split(s[0])*seconds;
        }
        return num;
    }
    public static int split(String s){
        int num = 0,i,n;
      if(s.length()>0&&s.contains("百"))
      {
          String[] s1 = s.split("百");
          for(i = 0 ; i < CN.length();i++)
              if(s1[0].contains(CN.charAt(i)+""))
                  break;
          num = num + i*100;
          if (s1.length>1)
          s = s1[1];
          else
              s = null;
      }
      if(s.length()>0&&s.contains("十"))
      {
          String[] s1 = s.split("十");
         if(s1.length==0||s1[0]==""){
             i = 1;
         }else {
             for (i = 0; i < CN.length(); i++)
                 if (s1[0].contains(CN.charAt(i) + ""))
                     break;
         }
          num = num + i*10;
          if (s1.length>1)
          s = s1[1];
          else
              s = null;
      }
      if(s!=null) {
          for (i = 0; i < CN.length(); i++)
              if (s.contains(CN.charAt(i) + ""))
                  break;
          num = num + i;
      }
        return num;
    }
}
