package com.zeng;

import com.zeng.amqp.RabbitChatSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-09-08
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SendMsgTest {

    @Autowired
    RabbitChatSender rabbitChatSender;

    @Test
    public void send(){
        rabbitChatSender.send("hhahah");
    }

}
