package com.example.netty_lecture.fourthdemo;

import com.example.netty_lecture.seconddemo.SocketClient;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyClientHandler extends SimpleChannelInboundHandler<SocketClient> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketClient msg) throws Exception {

    }
}
