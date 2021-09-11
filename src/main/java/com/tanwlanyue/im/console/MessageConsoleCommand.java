package com.tanwlanyue.im.console;

import java.util.Scanner;

import com.tanwlanyue.im.packet.MessageRequestPacket;

import io.netty.channel.Channel;

/**
 * @author zhanglei211 on 2021/9/1.
 */
public class MessageConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入toUserId");
        String toUserid = scanner.nextLine();
        System.out.println("输入message");
        String message = scanner.nextLine();
        MessageRequestPacket packet = new MessageRequestPacket();
        packet.setMessage(message);
        packet.setToUserid(toUserid);
        channel.writeAndFlush(packet);
    }
}
