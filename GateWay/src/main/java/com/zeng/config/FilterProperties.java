package com.zeng.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @B白名单
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-08-18
 **/
@Data
@Component
@PropertySource(value = "classpath:application.yml")
@ConfigurationProperties(prefix = "filter")
public class FilterProperties {

    private List<String> whitePaths;
}
