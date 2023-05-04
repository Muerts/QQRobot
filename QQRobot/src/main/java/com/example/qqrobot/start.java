package com.example.qqrobot;

import com.example.qqrobot.Model.QQClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class start implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        if(!QQClient.connect("ws://127.0.0.1:9090"))
            QQClient.reconnect();
    }
}
