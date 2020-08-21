package com.example.netty_lecture.thirddemo;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channels.forEach(ch -> {
            if (channel == ch) {
                ch.writeAndFlush("自己: " + msg + "\n");
            } else {
                ch.writeAndFlush(channel.remoteAddress() + ": " + msg + "\n");
            }
        });
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " 加入");
        channels.writeAndFlush("[服务器]: " + ctx.channel().remoteAddress() + " 加入\n");
        channels.add(ctx.channel());
        System.out.println(channels.size());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " 离开");

        channels.writeAndFlush("[服务器]: " + ctx.channel().remoteAddress() + " 离开\n");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channels.writeAndFlush("[服务器]: " + ctx.channel().remoteAddress() + " 上线\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        channels.writeAndFlush("[服务器]: " + ctx.channel().remoteAddress() + " 下线\n");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
