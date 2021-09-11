package com.tanwlanyue.im.handler;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
import com.tanwlanyue.im.packet.CreateGroupRequestPacket;
import com.tanwlanyue.im.packet.CreateGroupResponsePacket;
import com.tanwlanyue.im.util.IDUtil;
import com.tanwlanyue.im.util.LogUtil;
import com.tanwlanyue.im.util.SessionUtil;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

/**
 * @author zhanglei211 on 2021/9/1.
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    private static final LogUtil log = new LogUtil(CreateGroupRequestHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket packet) throws Exception {
        List<String> includeUseridList = packet.getIncludeUseridList();
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
        ArrayList<String> usernameList = Lists.newArrayList();
        for (String userid : includeUseridList) {
            Channel channel = SessionUtil.getChannel(userid);
            if (channel != null) {
                channelGroup.add(channel);
                usernameList.add(SessionUtil.getSession(channel).getUsername());
            }
        }
        String groupId = IDUtil.randomId();
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setSuccess(true);
        createGroupResponsePacket.setGroupId(groupId);
        createGroupResponsePacket.setUsernameList(usernameList);
        log.info("创建群成功groupId：" + groupId + ",包含成员:" + usernameList);
        channelGroup.writeAndFlush(createGroupResponsePacket);
        SessionUtil.bindChannelGroup(groupId, channelGroup);
    }
}
