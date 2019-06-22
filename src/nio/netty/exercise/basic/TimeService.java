package nio.netty.exercise.basic;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.Future;

public class TimeService {
    public void bind(int port) throws InterruptedException {
        NioEventLoopGroup parentGroup = new NioEventLoopGroup();
        NioEventLoopGroup normalGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(parentGroup, normalGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    System.out.println("initChannel");
                    ch.pipeline().addLast(new ChannelHandlerAdapter() {

                        @Override
                        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                            System.out.println("exceptionCaught");
                            ctx.close();
                        }

                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            System.out.println("channelRead");
                            ByteBuf byteBuf = (ByteBuf) msg;
                            byte[] req = new byte[byteBuf.readableBytes()];
                            byteBuf.readBytes(req);
                            String body = new String(req, StandardCharsets.UTF_8);
                            System.out.println("服务器接收到请求 : " + body);
                            String res = "获取时间".equalsIgnoreCase(body) ? new java.util.Date(
                                    System.currentTimeMillis()).toString() : "错误请求";
                            ByteBuf resBuf = Unpooled.copiedBuffer(res.getBytes());
                            ctx.write(resBuf);
                        }

                        @Override
                        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                            System.out.println("channelReadComplete");
                            ctx.flush();
                        }
                    });
                }
            });
            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } finally {
            parentGroup.shutdownGracefully();
            normalGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new TimeService().bind(99);
    }
}
