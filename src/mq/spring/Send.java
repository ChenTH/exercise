package mq.spring;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Send {
    public static void main(final String... args) throws Exception {
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(
                "classpath:mq/spring/rabbitmq-send.xml");
        //RabbitMQ模板
        RabbitTemplate template = ctx.getBean(RabbitTemplate.class);
        //发送消息
        for (int i = 0; i < 100; i++) {
            template.convertAndSend("Hello World!");
            Thread.sleep(100*i);
        }
        Thread.sleep(1000);// 休眠1秒
        ctx.close(); //容器销毁
    }
}
