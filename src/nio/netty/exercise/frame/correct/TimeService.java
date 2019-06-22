package nio.netty.exercise.frame.correct;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.StandardCharsets;

public class TimeService {
    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup subGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(parentGroup, subGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024).childHandler(new ChannelInitializer<SocketChannel>() {

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
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            System.out.println("接收到请求数据");
                            String body = (String) msg;
                            System.out.println("请求数据为" + body);
                            System.out.println(body);
                            String reqs = body.substring(0, body.length() - System.getProperty("line.separator").length());
                            String res = "获取时间".equals(reqs) ? new java.util.Date(System.currentTimeMillis()).toString() : "错误请求";
                            res+=System.getProperty("line.separator");
                            System.out.println("响应数据为" + res);
                            ByteBuf byteBufRes = Unpooled.copiedBuffer(res.getBytes());
                            ctx.write(byteBufRes);
                            System.out.println("请求处理结束");
                        }

                        @Override
                        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                            ctx.flush();
                        }
                    });
                }
            });
            ChannelFuture channelFuture = serverBootstrap.bind(999).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            parentGroup.shutdownGracefully();
            subGroup.shutdownGracefully();
        }

    }
}
