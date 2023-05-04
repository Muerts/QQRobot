package com.example.qqrobot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.qqrobot.entity.Log;

/**
* @author Administrator
* @description 针对表【log】的数据库操作Mapper
* @createDate 2023-05-02 10:56:28
* @Entity com.example.qqrobot.entity.Log
*/
public interface LogMapper extends BaseMapper<Log> {
    int insertAll(Log log);
}




