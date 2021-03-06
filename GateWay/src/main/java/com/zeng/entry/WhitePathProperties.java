package com.zeng.entry;

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
@PropertySource(value = "classpath:bootstrap.yml")
@ConfigurationProperties(prefix = "filter")
public class WhitePathProperties {

    private List<String> whitePaths;
}
