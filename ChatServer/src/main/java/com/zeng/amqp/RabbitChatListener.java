package com.zeng.amqp;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-09-08
 **/
@Component
public class RabbitChatListener {


    @RabbitListener(bindings = @QueueBinding(
            value =@Queue(name = "boot_queue",durable = "true"),
            exchange = @Exchange(name = "boot_exchange",type = ExchangeTypes.DIRECT,durable ="true"),
            key = {"routekey"}
    ))
    public void receiveAmqpMessage(String msg,Channel channel,Message message) throws IOException {
        System.out.println("接受到消息-----"+msg);
        //手动确认
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
