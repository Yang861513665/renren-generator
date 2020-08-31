package io.renren.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;


public class NettyServer {
    public static void main(String[] args) throws Exception{
        int port = 8081;
        final NettyServerHandler serverHandler = new NettyServerHandler();
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup boss = new NioEventLoopGroup();
//        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            serverBootstrap
                    .group(boss)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(port)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        protected void initChannel(NioSocketChannel ch) {
                            ch.pipeline().addLast(serverHandler);
                        }
                    })
                    .bind(port).addListener(future -> {
                if (future.isSuccess()) {
                    System.out.println("端口[" + port + "]绑定成功!");
                } else {
                    System.err.println("端口[" + port + "]绑定失败!");
                }
            }).sync().channel().closeFuture().sync();
        }finally {
            boss.shutdownGracefully().sync();
        }

    }

    static class NettyServerHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf in = (ByteBuf) msg;
            // 简单打印客户端发送来的消息
            System.out.println("接受到来自客户端的消息: " + in.toString(CharsetUtil.UTF_8));
            // 响应客户端
            ctx.write(Unpooled.copiedBuffer("你好,客户端! 我是服务端", CharsetUtil.UTF_8));
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        }
    }
}