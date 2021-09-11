package com.tanwlanyue.im;

import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.tanwlanyue.im.console.ConsoleCommandManager;
import com.tanwlanyue.im.console.LoginConsoleCommand;
import com.tanwlanyue.im.handler.*;
import com.tanwlanyue.im.util.LogUtil;
import com.tanwlanyue.im.util.SessionUtil;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author zhanglei211 on 2021/8/27.
 */
public class NettyClient {

    private static final int MAX_RETRY = 10;
    private static final String HOST = "localhost";
    private static final int PORT = 8000;
    private static final LogUtil log = new LogUtil(NettyClient.class);

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        bootstrap.group(workerGroup).channel(NioSocketChannel.class).option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true).option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        channel.pipeline().addLast(new SplitFrameHandler());
                        channel.pipeline().addLast(new PacketDecoderHandler());
                        channel.pipeline().addLast(new LoginResponseHandler());
                        channel.pipeline().addLast(new MessageResponseHandler());
                        channel.pipeline().addLast(new CreateGroupResponseHandler());
                        channel.pipeline().addLast(new JoinGroupResponseHandler());
                        channel.pipeline().addLast(new QuitGroupResponseHandler());
                        channel.pipeline().addLast(new ListGroupMemberResponseHandler());
                        channel.pipeline().addLast(new GroupMessageResponseHandler());
                        channel.pipeline().addLast(new LogoutResponseHandler());
                        channel.pipeline().addLast(new PacketEncoderHandler());
                    }
                });
        connect(bootstrap, HOST, PORT, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                log.info("连接成功");
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
            } else if (retry == 0) {
                log.error("重试次数已用完，放弃连接");
            } else {
                int order = (MAX_RETRY - retry) + 1;
                log.info("第" + order + "次重试");
                int delay = 1 << order;
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay,
                        TimeUnit.SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(), r -> new Thread(r, "console"));
        Scanner scanner = new Scanner(System.in);
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        threadPoolExecutor.execute(() -> {
            while (!Thread.interrupted()) {
                if (!SessionUtil.hasLogin(channel)) {
                    loginConsoleCommand.exec(scanner, channel);
                } else {
                    consoleCommandManager.exec(scanner, channel);
                }
            }
        });
    }
}
