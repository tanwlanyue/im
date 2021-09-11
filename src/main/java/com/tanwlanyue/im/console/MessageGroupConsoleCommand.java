package com.tanwlanyue.im.console;

import java.util.Scanner;

import com.tanwlanyue.im.packet.GroupMessageRequestPacket;

import io.netty.channel.Channel;

/**
 * @author zhanglei211 on 2021/9/1.
 */
public class MessageGroupConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入group Id");
        String groupId = scanner.nextLine();
        System.out.println("输入message");
        String message = scanner.nextLine();
        GroupMessageRequestPacket groupMessageRequestPacket = new GroupMessageRequestPacket();
        groupMessageRequestPacket.setMessage(message);
        groupMessageRequestPacket.setToGroupId(groupId);
        channel.writeAndFlush(groupMessageRequestPacket);
    }
}
