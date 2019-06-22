package thread.class5.waittest2;

public class WaitTest2 {
    public static void main(String[] args) {
        ThreadA t1 = new ThreadA("t1");
        synchronized(t1) {
            try {
                // 启动“线程t1”
                System.out.println(Thread.currentThread().getName()+" start t1");
                System.out.println("线程t1的状态是:"+t1.getState());
                t1.start();
                // 主线程等待t1通过notify()唤醒。
                System.out.println("挂起主线程");
                System.out.println("线程t1的状态是:"+t1.getState());
                t1.wait();  //  不是使t1线程等待，而是让拥有t1这个对象的主线程等待
                System.out.println("线程t1的状态是:"+t1.getState());
                System.out.println("挂起主线程后面的输出");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}