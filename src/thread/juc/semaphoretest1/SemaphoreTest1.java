package thread.juc.semaphoretest1;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest1 {
    private static final int SEM_MAX = 10;

    public static void main(String[] args) {
        Semaphore sem = new Semaphore(SEM_MAX);
        //创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        //在线程池中执行任务
        threadPool.execute(new MyThread(sem, 5));
        threadPool.execute(new MyThread(sem, 4));
        threadPool.execute(new MyThread(sem, 7));
        //关闭池
        threadPool.shutdown();
    }
}