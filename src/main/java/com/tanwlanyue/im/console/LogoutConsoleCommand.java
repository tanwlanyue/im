package com.tanwlanyue.im.console;

import java.util.Scanner;

import com.tanwlanyue.im.packet.LogoutRequestPacket;

import io.netty.channel.Channel;

/**
 * @author zhanglei211 on 2021/9/1.
 */
public class LogoutConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
        channel.writeAndFlush(logoutRequestPacket);
    }
}
