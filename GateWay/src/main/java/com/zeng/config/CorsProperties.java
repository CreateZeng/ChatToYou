package com.zeng.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-08-25
 **/
@Component
@Data
@PropertySource(value = "classpath:application.yml")
@ConfigurationProperties(prefix = "cors")
public class CorsProperties {
    private List<String> allowedOrigins;
    private Boolean allowedCredentials;
    private List<String> allowedMethods;
    private List<String> allowedHeaders;
    private Long maxAge;
    private String filterPath;
}
