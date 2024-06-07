package org.xunyin.officeautomationdemo.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Scanner;

public class WebSocketClient {
    public static void main(String[] args) throws Exception{
        NioEventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            socketChannel.pipeline().addLast(new WebSocketClientHandler());
                        }
                    });
            Channel channel = bootstrap.connect("localhost",8088)
                    .sync()
                    .channel();
            System.out.println("client搭建完成");
            System.out.println("输入你的用户名：");
            Scanner scanner = new Scanner(System.in);
            String userName = scanner.nextLine();
            channel.writeAndFlush(new TextWebSocketFrame("user" + userName));
            while (true){
                String message = scanner.nextLine();
                if("quit".equalsIgnoreCase(message)){
                    channel.closeFuture().sync();
                    break;
                }
            }
        }finally {
            group.shutdownGracefully();
        }

    }
}
