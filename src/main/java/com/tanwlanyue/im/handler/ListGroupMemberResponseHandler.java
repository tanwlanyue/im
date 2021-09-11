package com.tanwlanyue.im.handler;

import com.tanwlanyue.im.packet.ListGroupMemberResponsePacket;
import com.tanwlanyue.im.util.LogUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author zhanglei211 on 2021/9/1.
 */
public class ListGroupMemberResponseHandler extends SimpleChannelInboundHandler<ListGroupMemberResponsePacket> {

    private final LogUtil log = new LogUtil(ListGroupMemberResponseHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMemberResponsePacket packet) throws Exception {
        System.out.println("群成员：" + packet.getSessionList());
    }
}
