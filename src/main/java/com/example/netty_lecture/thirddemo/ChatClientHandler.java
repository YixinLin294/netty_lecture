package com.example.netty_lecture.thirddemo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ChatClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
//        System.out.println(ctx.channel().remoteAddress());
        System.out.println(msg);
    }
}
