package com.test.nettytest.Client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

@ChannelHandler.Sharable
public class ClientHandler extends SimpleChannelInboundHandler {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss").format(new Date()))
        .append(" - 커맨드 전송 결과 : ")
        .append(((ByteBuf)msg).toString(CharsetUtil.UTF_8));
        System.out.println(sb.toString());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }

}
