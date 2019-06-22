package spring.ioc.spring.xml;

import org.springframework.beans.factory.FactoryBean;
import spring.ioc.Car;

public class CarFactoryBean implements FactoryBean<Car> {
    @Override
    public Car getObject() throws Exception {
        Car car = new Car();
        car.setHeight("1000");
        car.setLength("1000");
        car.setName("1000");
        car.setWidth("1000");
        return car;
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
