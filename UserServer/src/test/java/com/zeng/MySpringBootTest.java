package com.zeng;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-08-25
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MySpringBootTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void test(){
        logger.debug("哈哈哈哈");
        logger.info("sdddddd");
    }
}
