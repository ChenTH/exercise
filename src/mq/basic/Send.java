package mq.basic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import mq.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
    private static String QUEUE_NAME = "q_test_01";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取到链接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        //从链接中创建通道
        Channel channel = connection.createChannel();
        //声明链接
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //消息内容
        String message = "Hello World";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        //关闭通道
        channel.close();
        connection.close();
    }
}
