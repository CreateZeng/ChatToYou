package com.zeng.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Collection;
import java.util.HashMap;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-08-21
 * @自定义redisTemplate
 **/
@Configuration
public class RedisTemplateConfig {

    /**
     * @自定义redisTemplate
     * @Params:
     * @Return:
     *
    */
    @Bean("RedisObject")
    public RedisTemplate<String,Object> setRedisTemplate01(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //设置Key的序列化
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //默认JDK序列化、修改成FastJson序列化(序列化内容)
        FastJsonRedisSerializer<Object> fastJsonSerializer = new FastJsonRedisSerializer<>(Object.class);
        redisTemplate.setDefaultSerializer(fastJsonSerializer);
        return redisTemplate;
    }
    @Bean("RedisString")
    public RedisTemplate<String,String> setRedisTemplate02(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        //设置Key的序列化
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //默认JDK序列化、修改成FastJson序列化(序列化内容)
        FastJsonRedisSerializer<Object> fastJsonSerializer = new FastJsonRedisSerializer<>(Object.class);
        redisTemplate.setDefaultSerializer(fastJsonSerializer);
        return redisTemplate;
    }
}
