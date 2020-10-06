package com.zeng;

import com.rabbitmq.client.*;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-09-07
 **/
public class RabbitSendMsg {

    private static final String QUEUE_NAME="boot_queue";

    private static final String EXCHANGE_NAME="boot_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
//        FanoutTypeMQ();
//        BasicTypeMQ();
        DirectTypeMQ();
    }

    /**
     * @基本类型使用
     * @Params:
     * @Return:
     */
    private static void BasicTypeMQ() throws IOException, TimeoutException {

        Connection connection = RabbitUtil.getConnection();
        //创建channel
        Channel channel = connection.createChannel();

        // 声明队列----参数:1.队列名 2.是否持久化 3.是否只有单消费者 4.是否自动删除 5.设定删除时间
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        // 向指定的队列中发送消息----- 1.交换机 2.队列名 3.路由头 4.消息内容
        for (int i = 0; i < 50; i++) {
            String msg="你好世界"+i;
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        }

        //关闭连接
        channel.close();
        connection.close();
    }

    /**
     * @订阅模式--fanout(广播)的使用
     * @Params:
     * @Return:
     *
    */
    public static void FanoutTypeMQ() throws IOException, TimeoutException {
        Connection connection = RabbitUtil.getConnection();
        Channel channel = connection.createChannel();
        //绑定交换机  交换机exchang、类型fanout
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        // 向指定的队列中发送消息----- 1.交换机 2.队列名 3.路由头 4.消息内容
//        for (int i = 0; i < 50; i++) {
//            String msg="你好世界"+i;
//            channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());
//        }
        String msg="你好世界";
        channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());
        //关闭连接
        channel.close();
        connection.close();
    }

    /**
     * @订阅模式---durect的使用
     * @Params:
     * @Return:
     *
     */
    private static void DirectTypeMQ() throws IOException, TimeoutException {
        Connection connection = RabbitUtil.getConnection();
        Channel channel = connection.createChannel();
        //绑定交换机  交换机exchang、类型fanout
//        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        //持久化、防止消息丢失
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT,true);

        // 向指定的队列中发送消息----- 1.交换机 2.队列名 3.路由头 4.消息内容
//        for (int i = 0; i < 50; i++) {
//            String msg="你好世界"+i;
//            channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());
//        }
        String msg="hhaha";
        //并且指定routing key 1.交换机 2.队列名 3.路由头 4.消息内容
        channel.basicPublish(EXCHANGE_NAME, "routeKey", null, msg.getBytes());
        //关闭连接
        channel.close();
        connection.close();
    }
}
