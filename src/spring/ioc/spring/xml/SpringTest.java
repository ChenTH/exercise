package spring.ioc.spring.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.ioc.Wheel;

public class SpringTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/spring/ioc/spring/xml/spring.xml");
//        Car car1 = (Car) applicationContext.getBean("car-with-autowire");
//        Car car2 = (Car) applicationContext.getBean("car-without-autowire");
//        Car car3 = (Car) applicationContext.getBean("car-contextaware");
//        Car car4 = (Car) applicationContext.getBean("car-lookup");
        Wheel wheel = (Wheel) applicationContext.getBean("wheel1");

//        System.out.println(car1.getWheel());
//        System.out.println(car2.getWheel());
//        System.out.println(applicationContext.getBean("car-beanfactory"));
//        System.out.println(applicationContext.getBean("&car-beanfactory"));
//        System.out.println(car3.getWheel());
//        System.out.println(car3.getWheel());
//        System.out.println(car4.getWheel());
//        System.out.println(car4.getWheel());
        System.out.println(wheel);
    }
}
