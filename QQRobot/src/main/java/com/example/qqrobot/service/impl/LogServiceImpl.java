package com.example.qqrobot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.qqrobot.entity.Log;
import com.example.qqrobot.service.LogService;
import com.example.qqrobot.mapper.LogMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【log】的数据库操作Service实现
* @createDate 2023-05-02 10:56:28
*/
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log>
    implements LogService{

}




