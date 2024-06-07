package org.xunyin.officeautomationdemo.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.HashMap;
import java.util.Map;

public class WebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static HashMap<Channel,String> users = new HashMap<>();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame message) throws Exception {
        String content = message.text();
        if (content.matches("user")){
            users.put(ctx.channel(), content);
        }
        clients.forEach(channel -> {
            if (channel != ctx.channel()){
                channel.writeAndFlush( users.get(ctx.channel()) + ": " + content);
            }
            else {
                channel.writeAndFlush("我：" + content);
            }
        });

    }
    @Override
    public void handlerAdded(ChannelHandlerContext ctx)throws Exception{

        clients.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx){
        clients.remove(ctx.channel());
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

}
