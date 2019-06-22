package spring.ioc.spring.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.ioc.spring.annotation.Wheel;

public class SpringTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/spring/ioc/spring/annotation/spring.xml");
        Wheel wheel = (Wheel) applicationContext.getBean("wheel");
        Car car = (Car) applicationContext.getBean("car");
//        System.out.println(car1.getWheel());
//        System.out.println(car2.getWheel());
//        System.out.println(applicationContext.getBean("car-beanfactory"));
//        System.out.println(applicationContext.getBean("&car-beanfactory"));
//        System.out.println(car3.getWheel());
//        System.out.println(car3.getWheel());
//        System.out.println(car4.getWheel());
//        System.out.println(car4.getWheel());
        System.out.println(wheel.getBrand());
        System.out.println(wheel);
        System.out.println(car.getName());
        System.out.println(car.getWheel());
    }
}
