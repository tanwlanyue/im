package com.tanwlanyue.im.handler;

import com.tanwlanyue.im.packet.CreateGroupResponsePacket;
import com.tanwlanyue.im.util.LogUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author zhanglei211 on 2021/9/1.
 */
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {

    private final LogUtil log = new LogUtil(CreateGroupResponseHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket packet) throws Exception {
        log.info("group id:" + packet.getGroupId());
        log.info("username List" + packet.getUsernameList());
    }
}
