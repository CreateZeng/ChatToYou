package com.zeng.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-09-02
 **/
@Configuration
@EnableSwagger2       //开启在线接口文档
public class SwaggerUIConfig {

    /**
     * @配置SwaggerUI的摘要信息---Docket
     * @Params: 
     * @Return:
    */
    @Bean
    public Docket controllerApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(createApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zeng.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * @获取api信息--Apiinfo
     * @Params:
     * @Return:
     *
    */
    private ApiInfo createApiInfo(){
        return new ApiInfoBuilder()
                .title("WEBSOCKET接口文档API")
                .contact(new Contact("灰色眼泪",null,null))
                .version("Version--1.0")
                .build();
    }
}
