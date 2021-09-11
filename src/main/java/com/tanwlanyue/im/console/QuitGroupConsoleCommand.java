package com.tanwlanyue.im.console;

import java.util.Scanner;

import com.tanwlanyue.im.packet.QuitGroupRequestPacket;

import io.netty.channel.Channel;

/**
 * @author zhanglei211 on 2021/9/1.
 */
public class QuitGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        QuitGroupRequestPacket quitGroupRequestPacket = new QuitGroupRequestPacket();
        System.out.print("输入 groupId，退出群聊：");
        String groupId = scanner.next();
        quitGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(quitGroupRequestPacket);
    }
}
