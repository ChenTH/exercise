package thread.interrupted;

import org.json.Test;

public class Main {

    public static void main(String[] args) {
//        TestThread testThread = new TestThread();
//        testThread.start();
        System.out.println(Thread.currentThread().isInterrupted());
        System.out.println(Thread.currentThread().isInterrupted());
        Thread.currentThread().interrupt();
        System.out.println(Thread.currentThread().isInterrupted());
        System.out.println(Thread.currentThread().isInterrupted());

    }
}
