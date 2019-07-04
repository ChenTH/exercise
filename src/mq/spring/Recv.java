package mq.spring;

import com.rabbitmq.client.Connection;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Recv {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:mq/spring/rabbitmq-recv.xml");
        while (true) {

        }
    }
}
