package nio.netty.exercise.frame.correct;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.StandardCharsets;

public class TimeClient {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new ChannelHandlerAdapter() {

                                @Override
                                public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                    ctx.close();
                                }

                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    System.out.println("连接成功，发送请求数据");
                                    String req = "请求时间" + System.getProperty("line.separator");
                                    for (int i = 0; i < 100; i++) {
                                        ByteBuf byteBuf = Unpooled.copiedBuffer(req.getBytes());
//                                        byteBuf.writeBytes(req.getBytes());
                                        ctx.writeAndFlush(byteBuf);
                                    }
                                    System.out.println("发送请求数据结束");
                                }

                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    System.out.println("接收数据开始");
                                    String res = (String)msg;
                                    System.out.println(res);
                                    System.out.println("接收数据结束");
                                }
                            });
                        }
                    });
            ChannelFuture f = b.connect("127.0.0.1", 999).sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
