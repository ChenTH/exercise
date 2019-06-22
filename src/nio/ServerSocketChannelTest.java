package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ServerSocketChannelTest {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(99));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            int readyOperation = selector.select();
            if (readyOperation == 0) continue;
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
            while (selectionKeyIterator.hasNext()) {
                SelectionKey selectionKey = selectionKeyIterator.next();
                if (selectionKey.isAcceptable()) {
                    System.out.println("有链接连进来了");
                    ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_CONNECT);
                }
                if (selectionKey.isConnectable()) {
                    System.out.println("connect");
                }
                if (selectionKey.isReadable()) {
                    System.out.println("我在读数据");
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int i = 0;
                    while ((channel.read(byteBuffer) != -1)) {
                        String a = new String(byteBuffer.array()).trim();
                        System.out.println(a);
                        byteBuffer.clear();
                    }
                    selectionKey.interestOps(SelectionKey.OP_WRITE);
                }
                if (selectionKey.isWritable()) {
                    System.out.println("我在写数据");
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    byte[] bytes = "你好啊".getBytes();
                    ByteBuffer outBuffer = ByteBuffer.allocate(bytes.length);
                    outBuffer.put(bytes);
                    outBuffer.flip();
                    channel.write(outBuffer);
                    selectionKey.interestOps();
                }
                selectionKeyIterator.remove();
            }
        }
    }

}
