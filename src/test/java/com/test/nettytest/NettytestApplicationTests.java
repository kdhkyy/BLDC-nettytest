package com.test.nettytest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NettytestApplicationTests {

    @Test
    public static void main(String[] args) {
        String s = "\u0002" + "PARA CALL" + "\u0003";
        System.out.println(s + "!!!!!!!!!!!!");
        byte[] b = new byte[32];
        System.out.println(b.toString());
    }

    @Test
    void contextLoads() {
    }

}
