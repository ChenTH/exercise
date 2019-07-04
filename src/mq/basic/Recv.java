package mq.basic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.QueueingConsumer;
import mq.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv {
    private static String QUEUE_NAME = "q_test_01";

    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {
        //获取到链接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        //监听队列
        channel.basicConsume(QUEUE_NAME, true, consumer);
        //获取消息
        while (true){
            QueueingConsumer.Delivery delivery= consumer.nextDelivery();
            String message=new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");
        }
    }
}
