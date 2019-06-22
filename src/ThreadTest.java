public class ThreadTest implements Runnable {

    private int ticket = 10;

    @Override
    public synchronized void run() {
        while (ticket > 0) {
            try {
                wait(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (ticket > 0)
                System.out.println(Thread.currentThread().getName()
                        + " solds tickets,tickets " + ticket--);
            notifyAll();

        }
    }

    public static void main(String[] args) {

        ThreadTest rt = new ThreadTest();

        Thread t1 = new Thread(rt);
        Thread t2 = new Thread(rt);
        Thread t3 = new Thread(rt);

        t1.start();
        t2.start();
        t3.start();

    }

}