package thread.class4.demo1_2;

public class MyThread extends Thread {

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100); // 休眠100ms
                    System.out.println(Thread.currentThread().getName() + " loop " + i);
                }
            } catch (InterruptedException ie) {
            }
        }
    }
}
