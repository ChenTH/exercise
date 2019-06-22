package thread.class5.waittimeouttest;

class ThreadA extends Thread {

    public ThreadA(String name) {
        super(name);
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " run ");
        // 死循环，不断运行。
        while (true)
            ;
    }
}