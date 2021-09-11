package com.tanwlanyue.im.handler;

import com.tanwlanyue.im.packet.GroupMessageRequestPacket;
import com.tanwlanyue.im.packet.GroupMessageResponsePacket;
import com.tanwlanyue.im.util.SessionUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author zhanglei211 on 2021/9/1.
 */
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket packet) throws Exception {
        String toGroupId = packet.getToGroupId();
        String message = packet.getMessage();
        GroupMessageResponsePacket groupMessageResponsePacket = new GroupMessageResponsePacket();
        groupMessageResponsePacket.setMessage(message);
        groupMessageResponsePacket.setFromUser(SessionUtil.getSession(ctx.channel()));
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(toGroupId);
        channelGroup.writeAndFlush(groupMessageResponsePacket);
    }
}
