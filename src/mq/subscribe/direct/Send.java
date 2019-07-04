package mq.subscribe.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import mq.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {

    private static final String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        //声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        //填写消息并发送
        String message = "Hello World!";
        channel.basicPublish(EXCHANGE_NAME, "delete", null, message.getBytes());
        channel.basicPublish(EXCHANGE_NAME, "update", null, message.getBytes());
        channel.basicPublish(EXCHANGE_NAME, "select", null, message.getBytes());
        channel.basicPublish(EXCHANGE_NAME, "insert", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
