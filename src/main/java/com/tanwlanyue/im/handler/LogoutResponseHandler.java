package com.tanwlanyue.im.handler;

import com.tanwlanyue.im.packet.LogoutResponsePacket;
import com.tanwlanyue.im.util.SessionUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author zhanglei211 on 2021/8/31.
 */
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket packet) throws Exception {
        SessionUtil.unbind(ctx.channel());
    }
}
