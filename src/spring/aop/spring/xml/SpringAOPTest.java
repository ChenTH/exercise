package spring.aop.spring.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.aop.spring.xml.Subject;

public class SpringAOPTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(SpringAOPTest.class.getResource("/spring/aop/spring/xml/SpringAOP.xml").toString());
        Subject subject1 = (Subject) applicationContext.getBean("subjectImpl1");
        Subject subject2 = (Subject) applicationContext.getBean("subjectImpl2");
        subject1.login();
        subject1.download();
        subject2.login();
        subject2.download();
    }
}
