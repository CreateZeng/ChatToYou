package com.zeng.controller;/*
 * Package: com.zeng.controller
 * Author: Mr.Z.J---🐎
 * Date: 2020-08-26
 * Desc:
 */

import com.alibaba.fastjson.JSONObject;
import com.zeng.amqp.RabbitChatSender;
import com.zeng.entry.vo.ReturnResult;
import com.zeng.service.GoodService;
import org.apache.catalina.connector.Response;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/chat")
public class ChatController{

    @Autowired
    private RabbitChatSender rabbitChatSender;
    @Autowired
    private GoodService goodService;

    @PostMapping("/rabbit")
    public ResponseEntity<ReturnResult> rabbitTest(@RequestBody JSONObject jsonObject){
        boolean flag = rabbitChatSender.send(jsonObject.getString("msg"));
        if (!flag){
            return ResponseEntity.ok(ReturnResult.getFail("发送失败"));
        }
        return ResponseEntity.ok(ReturnResult.getSuccess("发送成功"));
    }

    @GetMapping("/good")
    public ResponseEntity<ReturnResult> AddGoods(){
        goodService.CreateIndic();
        return ResponseEntity.ok(ReturnResult.getSuccess("添加成功"));
    }
}
