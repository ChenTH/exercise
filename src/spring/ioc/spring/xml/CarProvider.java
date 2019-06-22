package spring.ioc.spring.xml;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import spring.ioc.Car;
import spring.ioc.Wheel;

public class CarProvider extends Car implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    public Wheel getWheel() {
        return (Wheel) applicationContext.getBean("wheel");
    }

    public void setWheel(Wheel wheel) {
        super.setWheel(wheel);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
