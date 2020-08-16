package com.zeng;/*
 * Package: com.zeng
 * Author: Mr.Z.J---🐎
 * Date: 2020-08-16
 * Desc:
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void TestDemo(){

    }
}
