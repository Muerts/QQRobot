package com.example.qqrobot;


import com.example.qqrobot.service.ExpressionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QqRobotApplicationTests {

    @Autowired
    ExpressionService expressionService;
    @Test
    void contextLoads() {
        System.out.println(expressionService.list());


    }

}
