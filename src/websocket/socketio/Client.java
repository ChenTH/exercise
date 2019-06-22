package websocket.socketio;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.client.Url;
import io.socket.emitter.Emitter;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Observable;
import java.util.concurrent.atomic.AtomicInteger;

public class Client {
    public static volatile AtomicInteger count = new AtomicInteger(0);
    public static volatile int count1 = 0;

    public static void main(String[] args) throws URISyntaxException {
        URI uri = new URI("http://192.168.1.104:8081/");
        Socket socket = IO.socket(uri);
        socket.on("info.message", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                for (Object o : args) {
                    System.out.println(o);
                }
                getAndIncrementCount();
            }
        }).on("struct.message", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                for (Object o : args) {
                    System.out.println(o);
                }
                getAndIncrementCount();

            }
        }).on("alarm.message", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                for (Object o : args) {
                    System.out.println(o);
                }
                getAndIncrementCount();

            }
        }).on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... objects) {
                System.out.println(Socket.EVENT_CONNECT);
                for (Object o : args) {
                    System.out.println(o);
                }
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... objects) {
                System.out.println(Socket.EVENT_CONNECT);
                for (Object o : args) {
                    System.out.println(o);
                }
            }
        }).on(Socket.EVENT_ERROR, new Emitter.Listener() {
            @Override
            public void call(Object... objects) {
                System.out.println(Socket.EVENT_CONNECT);
                for (Object o : args) {
                    System.out.println(o);
                }
            }
        }).on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
            @Override
            public void call(Object... objects) {
                System.out.println(Socket.EVENT_CONNECT);
                for (Object o : args) {
                    System.out.println(o);
                }
            }
        });
        socket.connect();
    }

    public static void getAndIncrementCount() {
        int i = count.getAndIncrement();
        System.out.println(count1);
        count1++;
        System.out.println(i);
    }
}
