package mq.subscribe.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import mq.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

public class Send {
    private static final String EXCHANGE_NAME = "test_exchange_topic";
    private final static String QUEUE_NAME1 = "test_queue_topic_work_1";
    private final static String QUEUE_NAME2 = "test_queue_topic_work_2";


    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        channel.queueDeclare(QUEUE_NAME1, false, false, false, null);
        channel.queueDeclare(QUEUE_NAME2, false, false, false, null);
        channel.queueBind(QUEUE_NAME1, EXCHANGE_NAME, "key.*");
        channel.queueBind(QUEUE_NAME2, EXCHANGE_NAME, "key.2");
        String message = "Hello World";
        channel.basicPublish(EXCHANGE_NAME, "key.1", null, message.getBytes());
        channel.basicPublish(EXCHANGE_NAME, "key.2", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        channel.close();
        connection.close();
    }
}
