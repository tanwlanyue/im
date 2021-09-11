package com.tanwlanyue.im.handler;

import java.util.List;

import com.tanwlanyue.im.packet.ListGroupMemberRequestPacket;
import com.tanwlanyue.im.packet.ListGroupMemberResponsePacket;
import com.tanwlanyue.im.util.Session;
import com.tanwlanyue.im.util.SessionUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author zhanglei211 on 2021/9/1.
 */
public class ListGroupMemberRequestHandler extends SimpleChannelInboundHandler<ListGroupMemberRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMemberRequestPacket packet) throws Exception {
        String groupId = packet.getGroupId();
        List<Session> sessionList = SessionUtil.getSessionList(groupId);
        ListGroupMemberResponsePacket listGroupMemberResponsePacket = new ListGroupMemberResponsePacket();
        listGroupMemberResponsePacket.setGroupId(groupId);
        listGroupMemberResponsePacket.setSessionList(sessionList);
        ctx.channel().writeAndFlush(listGroupMemberResponsePacket);
    }
}
