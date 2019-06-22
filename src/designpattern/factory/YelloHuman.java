package designpattern.factory;

public class YelloHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("yello");
    }

    @Override
    public void talk() {
        System.out.printf("can know");
    }
}
