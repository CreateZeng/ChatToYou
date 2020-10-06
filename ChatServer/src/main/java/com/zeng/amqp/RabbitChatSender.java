package com.zeng.amqp;

import com.zeng.config.RabbitProperties;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-09-09
 **/
@Component
public class RabbitChatSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RabbitProperties rabbitProperties;

    public boolean send(String msg){
        //发送消息
        try {
            rabbitTemplate.convertAndSend(rabbitProperties.getEx(), rabbitProperties.getKey(), msg);
        } catch (AmqpException e) {
            return false;
        }
        return true;
    }
}
