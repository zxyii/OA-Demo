package org.xunyin.officeautomationdemo.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import org.springframework.stereotype.Component;

@Component
public class WebSocketServer {
    public static void main(String[] args)throws Exception {
        //主线程组 用于接收客户端的连接请求 不做任何处理
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //从线程组 处理主线程组发布的任务
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //创建netty服务器
            ServerBootstrap serverBootstrap = new ServerBootstrap();//启动类
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) {
                    //给pipeline管道设置处理器 初始化管道
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    pipeline.addLast(new HttpServerCodec());
                    pipeline.addLast(new HttpObjectAggregator(64*1024));
                    pipeline.addLast(new WebSocketServerProtocolHandler("/websocket"));
                    pipeline.addLast(new WebSocketServerHandler());

                }
            });
            ChannelFuture channelFuture = serverBootstrap.bind(8088).sync();
            System.out.println("WebSocketServer启动");
            channelFuture.channel().closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
