package org.xunyin.officeautomationdemo.chat;

import io.netty.channel.*;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.util.concurrent.EventExecutorGroup;

public class WebSocketClientHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {

    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object message) throws Exception {
        Channel channel = ctx.channel();
        String content = ((TextWebSocketFrame)message).text();
        System.out.println("消息：" + content);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }

}
