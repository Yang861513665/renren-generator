package io.renren.netty;


import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        final NettyClientHandler clientHandler = new NettyClientHandler();

        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress("127.0.0.1", 8081)
                    .handler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) {
                            ch.pipeline().addLast(clientHandler);
                        }
                    }).connect().sync()
            .channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully().sync();
        }
    }

    static class NettyClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            // 连接建立给服务端发送消息
            ctx.writeAndFlush(Unpooled.copiedBuffer("我是客户端!", Charset.defaultCharset()));
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
            // 简单打印服务端回传的消息
            System.out.println("来自服务端的响应: " + in.toString(CharsetUtil.UTF_8));
        }
    }
}