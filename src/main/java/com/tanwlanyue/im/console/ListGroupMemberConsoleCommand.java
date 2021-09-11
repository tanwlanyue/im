package com.tanwlanyue.im.console;

import java.util.Scanner;

import com.tanwlanyue.im.packet.ListGroupMemberRequestPacket;

import io.netty.channel.Channel;

/**
 * @author zhanglei211 on 2021/9/1.
 */
public class ListGroupMemberConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        ListGroupMemberRequestPacket listGroupMemberRequestPacket = new ListGroupMemberRequestPacket();
        System.out.print("输入 groupId，获取群成员列表：");
        String groupId = scanner.next();
        listGroupMemberRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(listGroupMemberRequestPacket);
    }
}
