package com.zeng.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-08-25
 **/
@Configuration
public class GlobalCorsConfiguration {

    /**
     * @Cors跨域访问配置
     * @Params:
     * @Return:
    */
    @Bean
    public CorsFilter corsFilter(CorsProperties corsProperties){
        //添加cors配置
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //添加源origin
        for (String origin : corsProperties.getAllowedOrigins()) {
            corsConfiguration.addAllowedOrigin(origin);
        }
        //设置证书
        corsConfiguration.setAllowCredentials(corsProperties.getAllowedCredentials());
        //添加允许的请求方法(允许方法)
        for (String method : corsProperties.getAllowedMethods()) {
            corsConfiguration.addAllowedMethod(method);
        }
        //添加允许的请求头
        for (String header : corsProperties.getAllowedHeaders()) {
            corsConfiguration.addAllowedHeader(header);
        }
        //添加允许时间
        corsConfiguration.setMaxAge(corsProperties.getMaxAge());
        //添加映射路径
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration(corsProperties.getFilterPath(), corsConfiguration);
        //装配配置
        return new CorsFilter(configSource);
    }
}
