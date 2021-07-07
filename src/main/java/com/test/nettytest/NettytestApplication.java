package com.test.nettytest;

import com.test.nettytest.Server.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NettytestApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(NettytestApplication.class, args);
        new Server().start();
    }
}
