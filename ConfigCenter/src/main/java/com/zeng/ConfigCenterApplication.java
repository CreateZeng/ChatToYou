package com.zeng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.net.Inet4Address;
import java.net.InterfaceAddress;
import java.net.UnknownHostException;


/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-09-01
 * @配置中心入口类
 **/
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class ConfigCenterApplication {


    public static void main(String[] args) throws UnknownHostException {
        System.out.println("本机为-----"+Inet4Address.getLocalHost().getHostAddress());
        SpringApplication.run(ConfigCenterApplication.class,args);
    }
}
