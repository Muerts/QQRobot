package com.example.qqrobot.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class pretreatment {
    public static  String getNumberFromString(String str){
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        String result = m.replaceAll("").trim();
        return result;
    }
    public static String extractDate(String temp){
        String list = null;
        if (!temp.isEmpty()){
            String regular = "[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])";
            Pattern p = Pattern.compile(regular);
            Matcher m = p.matcher(temp);
            while(m.find()){
                list = m.group(0);
            }
        }
        return list;

    }

}
