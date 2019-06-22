package io;

import thread.class5.waittest.ThreadA;
import thread.juc.countdownlatchtest1.CountDownLatchTest1;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

public class SocketTest implements Runnable {
    private String name;
    private static CountDownLatch countDownLatch = new CountDownLatch(100);

    SocketTest(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket("127.0.0.1", 99);
             OutputStream out = socket.getOutputStream();) {
            for (int i = 0; i < 100; i++) {
                new Thread(() -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println(threadName + "开始运行了");
                    String message = threadName + "在发送消息sdfsfsdfdfdffsfadfdafsdfdsfasdfsadfadsfdsfsdfsdfdsfasdfadsfdasfasdfdsafdsafsdfsdfsdf" + System.getProperty("line.separator");
                    try {
                        out.write(message.getBytes());
                        System.out.println(Thread.currentThread().getName() + "发送结束了");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                        out.flush();
                    countDownLatch.countDown();
                    System.out.println(Thread.currentThread().getName() + "运行结束了");
                }).start();
            }
//            OutputStream out = socket.getOutputStream();
//            String message = name + "在发送消息";
//            out.write(message.getBytes());
//            out.flush();
//            socket.shutdownOutput();
//            out = socket.getOutputStream();
//            out.write(message.getBytes());
//            out.flush();
//            socket.shutdownOutput();
//            InputStreamReader reader = new InputStreamReader(socket.getInputStream());
//            BufferedReader br = new BufferedReader(reader);
//            String info = null;
//            while ((info = br.readLine()) != null) {
//                System.out.println(info);
//            }
//            socket.shutdownInput();
//            br.close();
//            reader.close();
//            out.close();
            countDownLatch.await();
            System.out.println("运行结束了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
//        for (int i = 0; i < 11; i++) {
//            new Thread(new SocketTest(String.valueOf(i))).start();
//        }
        new Thread(new SocketTest(String.valueOf(0))).start();
    }
}
