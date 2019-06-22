package spring.ioc.simple;

import org.junit.Test;
import spring.ioc.Car;
import spring.ioc.Wheel;

public class SimpleIOCTest {
    @Test
    public void getBean() throws Exception {
        String location = SimpleIOC.class.getResource("/spring/ioc/simple/ioc.xml").getFile();
        System.out.println(location);
        SimpleIOC bf = new SimpleIOC(location);
        Wheel wheel = (Wheel) bf.getBean("wheel");
        System.out.println(wheel.getBrand());
        Car car = (Car) bf.getBean("car");
        System.out.println(car.getHeight());
    }
}