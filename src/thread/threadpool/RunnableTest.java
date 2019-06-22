package thread.threadpool;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.concurrent.Callable;

public class RunnableTest implements Callable<String> {
    private int taskName;

    public RunnableTest(int taskName) {
        this.taskName = taskName;
    }

    @Override
    public String call() throws Exception {
        System.out.println("我开始运行了" + this.taskName);
        double target = Math.random() * 2;
        if (target > 1.1) {
            return taskName + "大于1.1";
        } else if (target < 0.9) {
            return taskName + "小于0.9";
        }else {
            throw new RuntimeException("运行错误了");
        }
    }
}
