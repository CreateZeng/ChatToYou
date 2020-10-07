package com.zeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-09-01
 * @配置中心入口类
 **/
@SpringBootApplication
@EnableConfigServer
public class ConfigCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterApplication.class,args);
    }
}
