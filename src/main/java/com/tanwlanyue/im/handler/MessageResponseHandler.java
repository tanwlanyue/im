package com.tanwlanyue.im.handler;

import com.tanwlanyue.im.packet.MessageResponsePacket;
import com.tanwlanyue.im.util.LogUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author zhanglei211 on 2021/8/31.
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    private final LogUtil log = new LogUtil(MessageResponseHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket packet)
            throws Exception {
        System.out.println( packet.getFromUserName() + " 说: "
                + packet.getMessage());
    }
}
