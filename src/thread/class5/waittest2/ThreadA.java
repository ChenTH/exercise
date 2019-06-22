package thread.class5.waittest2;

class ThreadA extends Thread{
    public ThreadA(String name) {
        super(name);
    }
    public void run() {
        synchronized (this) {
            try {
                Thread.sleep(1000); //  使当前线阻塞 1 s确保在主线程wait()之前t1没有执行完并退出
            } catch (Exception e) {
                e.printStackTrace();
            }
            //System.out.println(Thread.currentThread().getName()+" call notify()");
            // 唤醒当前的wait线程
            //this.notify();
        }
    }
}
