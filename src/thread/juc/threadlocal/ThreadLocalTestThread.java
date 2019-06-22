package thread.juc.threadlocal;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalTestThread implements Runnable {

    private static AtomicInteger nextId = new AtomicInteger(0);
    private static final ThreadLocal<Integer> threadId = ThreadLocal.withInitial(() -> nextId.getAndIncrement());

    @Override
    public void run() {
        System.out.println("currentThread Id is:" + threadId.get());
    }

    public static void main(String[] args) {
        new Thread(new ThreadLocalTestThread()).start();
        new Thread(new ThreadLocalTestThread()).start();
    }
}
