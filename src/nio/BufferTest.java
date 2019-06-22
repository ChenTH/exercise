package nio;

import java.nio.ByteBuffer;

public class BufferTest {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("你好啊".getBytes());
        byteBuffer.flip();
        String a = new String(byteBuffer.array()).trim();
        System.out.println(a);
    }

}
