package thread.interrupted;

public class TestThread extends Thread {

    @Override
    public void run() {
        System.out.println(isInterrupted());
        System.out.println(isInterrupted());
    }
}
