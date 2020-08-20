package com.zeng;/*
 * Package: com.zeng
 * Author: Mr.Z.J---🐎
 * Date: 2020-08-16
 * Desc:
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatServerApplication {
    public static void main(String[] args) {
        SpringApplication aplication = new SpringApplication();
        SpringApplication.run(ChatServerApplication.class,args);
    }
}
