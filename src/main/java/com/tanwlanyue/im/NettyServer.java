package com.tanwlanyue.im;

import com.tanwlanyue.im.handler.*;
import com.tanwlanyue.im.util.LogUtil;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author zhanglei211 on 2021/8/27.
 */
public class NettyServer {

    private static final int PORT = 8000;

    private static final LogUtil log = new LogUtil(NettyServer.class);

    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024).childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true).childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception {
                        channel.pipeline().addLast(new SplitFrameHandler());
                        channel.pipeline().addLast(new PacketDecoderHandler());
                        channel.pipeline().addLast(new LoginRequestHandler());
                        channel.pipeline().addLast(new AuthHandler());
                        channel.pipeline().addLast(new MessageRequestHandler());
                        channel.pipeline().addLast(new CreateGroupRequestHandler());
                        channel.pipeline().addLast(new JoinGroupRequestHandler());
                        channel.pipeline().addLast(new QuitGroupRequestHandler());
                        channel.pipeline().addLast(new ListGroupMemberRequestHandler());
                        channel.pipeline().addLast(new GroupMessageRequestHandler());
                        channel.pipeline().addLast(new LogoutRequestHandler());
                        channel.pipeline().addLast(new PacketEncoderHandler());
                    }
                });
        bind(serverBootstrap, PORT);
    }

    static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                log.info("bind success,port:" + port);
            } else {
                log.error("bind failure port:" + port);
                bind(serverBootstrap, port + 1);
            }
        });
    }
}
