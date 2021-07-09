package com.test.nettytest.Client;

import com.test.nettytest.domain.SendDto;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;

import java.net.InetSocketAddress;

public class Client {
    private final String host;
    private final int port;

    private Channel serverChannel;
    private EventLoopGroup eventLoopGroup;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() throws InterruptedException {
        eventLoopGroup = new NioEventLoopGroup(2, new DefaultThreadFactory("client"));

        Bootstrap bootstrap = new Bootstrap().group(eventLoopGroup);

        bootstrap.channel(NioSocketChannel.class);
        bootstrap.remoteAddress(new InetSocketAddress(host, port));
        bootstrap.handler(new ClientInitializer());

        serverChannel = bootstrap.connect().sync().channel();
    }

    public void sendCommand(String command) throws InterruptedException {
        SendDto sendDto = new SendDto();
        sendDto.setCommand(command);
        ChannelFuture future;
        // Server로 전송
        future = serverChannel.writeAndFlush(sendDto.toString());

        // 종료되기 전 모든 메시지가 flush 될때까지 기다림
        if(future != null){
            future.sync();
        }
    }

    public void close() {
        eventLoopGroup.shutdownGracefully();
    }

}