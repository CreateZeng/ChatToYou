package com.zeng;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-09-07
 **/
public class RabbitReceiveMsgOne {

    private static final String QUEUE_NAME="boot_queue";

    private static final String EXCHANGE_NAME="boot_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
//        FanoutTypeMQ();
        DirectTypeMQ();
    }


    /**
     * @基本类型使用
     * @Params:
     * @Return:
     *
     */
    public static void BasicTypeMQ() throws IOException, TimeoutException {

        Connection connection = RabbitUtil.getConnection();
        Channel channel = connection.createChannel();

        //绑定队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.basicQos(1);
        // 定义队列的消费者(匿名内部类)
        DefaultConsumer consumer = new DefaultConsumer(channel) {

            // 获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                // body 即消息体
                String msg = new String(body);
                System.out.println("接受到信息---"+msg);
                // 手动进行ACK(自动确认不需要写)
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        // 监听队列，第二个参数：是否自动进行消息确认。
        channel.basicConsume(QUEUE_NAME, false, consumer);
    }


    /**
     * @订阅模式---fanout(广播)接受消息
     * @Params:
     * @Return:
     */
    private static void FanoutTypeMQ() throws IOException, TimeoutException {

        Connection connection = RabbitUtil.getConnection();
        Channel channel = connection.createChannel();

        //绑定队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //绑定交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");


        // 定义队列的消费者(匿名内部类)
        DefaultConsumer consumer = new DefaultConsumer(channel) {

            // 获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                // body 即消息体
                String msg = new String(body);
                System.out.println("接受到信息---"+msg);
                // 手动进行ACK(自动确认不需要写)
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        // 监听队列，第二个参数：是否自动进行消息确认。
        channel.basicConsume(QUEUE_NAME, false, consumer);
    }

    /**
     * @订阅模式---DIRECT接受消息
     * @Params:
     * @Return:
     */
    private static void DirectTypeMQ() throws IOException, TimeoutException {

        Connection connection = RabbitUtil.getConnection();
        Channel channel = connection.createChannel();

        //绑定队列 1.队列名 2.是否持久化 3.是否只有单消费者 4.是否自动删除 5.设定删除时间
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //绑定交换机
        // 绑定队列到交换机，同时指定需要订阅的routing key
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "routeKey");


        // 定义队列的消费者(匿名内部类)
        DefaultConsumer consumer = new DefaultConsumer(channel) {

            // 获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                // body 即消息体
                String msg = new String(body);
                System.out.println("接受到信息---"+msg);
                // 手动进行ACK(自动确认不需要写)
            //    channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        // 监听队列，第二个参数：是否自动进行消息确认。
        channel.basicConsume(QUEUE_NAME, false, consumer);
    }

    /**
     * @订阅模式---topic接受消息、相对direct可以使用通配符来匹配key
     * @Params:
     * @Return:
     */

}
