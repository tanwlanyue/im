package com.tanwlanyue.im.console;

import java.util.List;
import java.util.Scanner;

import com.google.common.collect.Lists;
import com.tanwlanyue.im.packet.CreateGroupRequestPacket;
import com.tanwlanyue.im.util.SessionUtil;

import io.netty.channel.Channel;

/**
 * @author zhanglei211 on 2021/9/1.
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {

    private static final String USERID_SPLIT = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();
        System.out.println("输入加入群聊的userid用 , 隔开");
        String userid = SessionUtil.getSession(channel).getUserid();
        String[] split = scanner.nextLine().split(USERID_SPLIT);
        List<String> includeUseridList = Lists.newArrayList(split);
        includeUseridList.add(userid);
        createGroupRequestPacket.setCreateUserid(userid);
        createGroupRequestPacket.setIncludeUseridList(includeUseridList);
        channel.writeAndFlush(createGroupRequestPacket);
    }
}
