package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class SocketChannelTest {


    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 99));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_WRITE);
        while (true) {
            int readyOperation = selector.select();
            if (readyOperation == 0) continue;
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
            while (selectionKeyIterator.hasNext()) {
                SelectionKey selectionKey = selectionKeyIterator.next();
                if (selectionKey.isReadable()) {
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    ByteBuffer inBuffer = ByteBuffer.allocate(1024);
                    while ((channel.read(inBuffer) != -1)) {
                        String a = new String(inBuffer.array()).trim();
                        System.out.println(a);
                        inBuffer.clear();
                    }
                    socketChannel.shutdownInput();
                    selectionKey.cancel();
                    socketChannel.close();
                }
                if (selectionKey.isValid() && selectionKey.isWritable()) {
                    byte[] bytes = "你好啊我是client".getBytes();
                    ByteBuffer outBuffer = ByteBuffer.allocate(bytes.length);
                    outBuffer.put(bytes);
                    outBuffer.flip();
                    socketChannel.write(outBuffer);
                    socketChannel.shutdownOutput();
                    selectionKey.interestOps(SelectionKey.OP_READ);
                }
                selectionKeyIterator.remove();
            }
        }
    }
}
