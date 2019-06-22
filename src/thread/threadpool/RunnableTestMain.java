package thread.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RunnableTestMain {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        List<Future<String>> futures = new ArrayList<>();
        /**
         * submit(Runnable x) 返回一个future。可以用这个future来判断任务是否成功完成。请看下面：
         */
        for (int i = 0; i < 100; i++) {
            Future future = pool.submit(new RunnableTest(i));
            futures.add(future);
        }
        Thread.sleep(1000);
        futures.forEach((Future<String> future) -> {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {

            } catch (ExecutionException e) {
                //否则我们可以看看任务失败的原因是什么
                System.out.println(e.getCause().getMessage());
            }
        });
    }
}
