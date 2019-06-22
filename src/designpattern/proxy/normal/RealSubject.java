package designpattern.proxy.normal;

public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("真实类运行了～");
    }
}
