package thread.class4.demo1_2;

public class Demo1_2 {
    public static void main(String[] args) {
        Thread mythread1 = new MyThread("1");
        Thread mythread2 = new MyThread("2");
        mythread1.start();
        mythread2.start();
    }
}
