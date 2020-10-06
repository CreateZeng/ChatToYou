package com.zeng.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-09-09
 **/
@Configuration
public class RabbitTemplateConfig {

    @Autowired
    private ConnectionFactory connectionFactory;
    @Autowired
    private FastJsonHttpMessageConverter messageConverter;

    @Bean
    public RabbitTemplate setConfig(){
        //创建rabbitTemplate
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
//        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        //消息发送确认
        RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean confirm, String error) {
                if (confirm){
                    System.out.println("发送成功");
                }else {
                    throw new RuntimeException("发送失败");
                }
            }
        };
        //设置发送确认
        rabbitTemplate.setConfirmCallback(confirmCallback);
        return rabbitTemplate;
    }
}
