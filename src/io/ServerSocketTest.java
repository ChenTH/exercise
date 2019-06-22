package io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerSocketTest {

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(99);
        while (true) {
            Socket socket = serverSocket.accept();
            executorService.execute(() -> {
                try {
                    InputStream in = socket.getInputStream();
                    InputStreamReader reader = new InputStreamReader(in);
                    BufferedReader br = new BufferedReader(reader);
                    String info = null;
                    while ((info = br.readLine()) != null) {
                        System.out.println(info);
                    }
                    socket.shutdownInput();
                    OutputStream out = socket.getOutputStream();
                    String s = "消息我已经收到";
                    out.write(s.getBytes());
                    out.flush();
                    socket.shutdownOutput();
                    out.close();
                    br.close();
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
