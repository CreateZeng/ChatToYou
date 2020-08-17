package com.zeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-08-17
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.zeng.mapper")
public class UserLoginApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication();
        application.run(UserLoginApplication.class,args);
    }
}
