package spring.aop.spring.annotate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAOPTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(SpringAOPTest.class.getResource("/spring/aop/spring/annotate/SpringAOP.xml").toString());
        Subject subject1 = (Subject) applicationContext.getBean("subject");
        subject1.login();
        subject1.download();
    }
}
