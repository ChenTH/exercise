package nio.netty.exercise.basic;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

public class TimeClient {

    public void connect(int port, String host) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    //todo 添加handler
                    ch.pipeline().addLast(new ChannelHandlerAdapter() {
                        @Override
                        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                            System.out.println("exceptionCaught");
                            ctx.close();
                        }

                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            System.out.println("channelActive");
                            ByteBuf firstMessage;
                            byte[] req = "获取时间".getBytes();
                            firstMessage = Unpooled.buffer(req.length);
                            firstMessage.writeBytes(req);
                            ctx.writeAndFlush(firstMessage);
                            System.out.println("channelActiveFinished");
                        }

                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            System.out.println("channelRead");
                            ByteBuf byteBuf = (ByteBuf) msg;
                            byte[] req = new byte[byteBuf.readableBytes()];
                            byteBuf.readBytes(req);
                            String body = new String(req, CharsetUtil.UTF_8);
                            System.out.println(body);
                        }
                    });
                }
            });
            ChannelFuture f = b.connect(host, port).sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new TimeClient().connect(99, "127.0.0.1");
    }
}
