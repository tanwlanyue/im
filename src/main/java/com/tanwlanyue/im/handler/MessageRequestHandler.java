package com.tanwlanyue.im.handler;

import com.tanwlanyue.im.packet.MessageRequestPacket;
import com.tanwlanyue.im.packet.MessageResponsePacket;
import com.tanwlanyue.im.util.LogUtil;
import com.tanwlanyue.im.util.Session;
import com.tanwlanyue.im.util.SessionUtil;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author zhanglei211 on 2021/8/31.
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    private static final LogUtil log = new LogUtil(MessageRequestHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket packet) throws Exception {
        Session session = SessionUtil.getSession(ctx.channel());
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(session.getUserid());
        messageResponsePacket.setFromUserName(session.getUsername());
        messageResponsePacket.setMessage(packet.getMessage());
        String toUserid = packet.getToUserid();
        Channel toUserChannel = SessionUtil.getChannel(toUserid);
        if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
            toUserChannel.writeAndFlush(messageResponsePacket);
        } else {
            log.error(toUserid + "不在线");
        }
    }
}
