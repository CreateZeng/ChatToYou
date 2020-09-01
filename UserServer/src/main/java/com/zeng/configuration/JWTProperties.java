package com.zeng.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-08-26
 **/
@Component
@Data
@PropertySource(value = "classpath:bootstrap.yml")
@ConfigurationProperties(prefix = "jwt.info")
public class JWTProperties {

    private int expire;
    private int surplus;
}
