package com.example.qqrobot.function;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.qqrobot.entity.Log;
import com.example.qqrobot.mapper.LogMapper;
import com.example.qqrobot.tool.pretreatment;
import jakarta.annotation.PostConstruct;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class saveFile {
    @Autowired
    LogMapper logMapper;
    static saveFile saveFile;
    static String HostQQ = "3307211295";
    static QueryWrapper<Log> logQuery = new QueryWrapper<>();
    static Log log = new Log();
    static SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
    @PostConstruct
    public void init(){
        saveFile = this;
        saveFile.logMapper = this.logMapper;
    }
    public static void sort(@NotNull String str){
        int status = 0;
        if(str.contains("/覆写")){status = 1; }
        else if(str.contains("/下文")){ status = 2; }
        else if(str.contains("/查询")){ status = 3; }
        switch (status)
        {
            case 1:overwriting(str);break;
            case 2:hereinafter(str);break;
            case 3:selecthistory(str);break;
            default:save(str);
        }
    }
    public static void overwriting(@NotNull String str){
        String[] split = str.split("/覆写");
        Date date = new Date();
        logQuery.eq("date",format.format(date));
        log.setContent(split[1]);
        log.setDate(format.format(date));
        saveFile.logMapper.update(log,logQuery);
        ToHostAndOtherTalk.toTalkWithQQ(HostQQ,"女儿已将您的对话重新记录在小本本上");
    }
    public static void hereinafter(String str){
        String[] split = str.split("/下文");
        Date date = new Date();
        logQuery.eq("date",format.format(date));
        log = saveFile.logMapper.selectOne(logQuery);
        log.setContent(log.getContent()+split[1]);
        saveFile.logMapper.update(log,logQuery);
        ToHostAndOtherTalk.toTalkWithQQ(HostQQ,"续写成功");
    }
    public static void save(@NotNull String str){
        String[] split = str.split("/日志");
        Date date = new Date();
        log.setContent(split[1]);
        log.setDate(format.format(date));
        saveFile.logMapper.insertAll(log);
        ToHostAndOtherTalk.toTalkWithQQ(HostQQ,"女儿已将您的对话记录在小本本上");
    }
    public static void selecthistory(String str){
        String date = pretreatment.extractDate(str);
        logQuery.eq("date",date);
        log = saveFile.logMapper.selectOne(logQuery); 
        ToHostAndOtherTalk.toTalkWithQQ(HostQQ,log.getContent());
    }

}
