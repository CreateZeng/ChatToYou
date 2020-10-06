package com.zeng.config;

import lombok.Data;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-09-09
 **/
@Data
@Component
@ConfigurationProperties(prefix = "ra")
public class RabbitProperties{

    private Map ques;

    private String ex;

    private String key;

}
