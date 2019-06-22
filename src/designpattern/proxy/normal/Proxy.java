package designpattern.proxy.normal;

public class Proxy implements Subject {
    private Subject subject;

    public Proxy() {
        this.subject = new RealSubject();
    }

    public Proxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {
        before();
        if (subject != null) {
            subject.request();
        }
        after();
    }

    private void before() {
        System.out.println("before");
    }

    private void after() {
        System.out.println("after");
    }

    public static void main(String args[]) {
        Proxy proxy = new Proxy(new RealSubject());
        proxy.request();
        System.out.println("普通代理");
        proxy = new Proxy();
        proxy.request();
    }
}
