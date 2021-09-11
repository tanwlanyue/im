package com.tanwlanyue.im.handler;

import com.tanwlanyue.im.packet.JoinGroupRequestPacket;
import com.tanwlanyue.im.packet.JoinGroupResponsePacket;
import com.tanwlanyue.im.util.SessionUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author zhanglei211 on 2021/9/1.
 */
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket packet) throws Exception {
        String groupId = packet.getGroupId();
        SessionUtil.getChannelGroup(groupId).add(ctx.channel());
        JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket();
        joinGroupResponsePacket.setGroupId(groupId);
        joinGroupResponsePacket.setSuccess(true);
        ctx.channel().writeAndFlush(joinGroupResponsePacket);
    }
}
