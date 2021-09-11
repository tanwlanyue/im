package com.tanwlanyue.im.handler;

import com.tanwlanyue.im.packet.QuitGroupRequestPacket;
import com.tanwlanyue.im.packet.QuitGroupResponsePacket;
import com.tanwlanyue.im.util.SessionUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author zhanglei211 on 2021/9/1.
 */
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket packet) throws Exception {
        String groupId = packet.getGroupId();
        SessionUtil.removeFromGroup(groupId, ctx.channel());
        QuitGroupResponsePacket quitGroupResponsePacket = new QuitGroupResponsePacket();
        quitGroupResponsePacket.setGroupId(groupId);
        quitGroupResponsePacket.setSuccess(true);
        ctx.channel().writeAndFlush(quitGroupResponsePacket);
    }
}
