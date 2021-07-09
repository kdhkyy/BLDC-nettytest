package com.test.nettytest.Server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {
    SimpleDateFormat format2;

    /*
     * 서버측 응답받을 때 호출
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        format2 = new SimpleDateFormat ( "yyyy/MM/dd HH:mm:ss");
        ByteBuf in = (ByteBuf) msg;
        String command = in.toString(CharsetUtil.UTF_8).replace("\u0002", "").replace("\u0003", "");
        StringBuilder sb = new StringBuilder();
        sb.append(format2.format(new Date()))
                .append(" - Server received : ")
                .append(in.toString(CharsetUtil.UTF_8))
                .append("\n" + format2.format(new Date()))
                .append(" - received command : ")
                .append(command);
        System.out.println(sb.toString());
        ctx.write(in);
    }

    /*
     * 응답 수신완료 시 호출
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
        System.out.println("수신완료");
    }

    /*
     * 응답 시 Exception 발생 시 호출
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
