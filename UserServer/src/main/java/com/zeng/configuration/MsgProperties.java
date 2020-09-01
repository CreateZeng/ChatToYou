package com.zeng.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-08-20
 **/
@Data
@Component
@PropertySource(value = "classpath:bootstrap.yml")
@ConfigurationProperties(prefix = "msg.sign")
public class MsgProperties {

    private String signName;  //签名名称

    private String templateCode;  //模板名称

    private String accessKeyId;  //accessKeyId

    private String accessKeySecret;  //accessKeySecret
}
