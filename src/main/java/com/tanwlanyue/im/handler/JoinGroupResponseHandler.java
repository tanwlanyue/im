package com.tanwlanyue.im.handler;

import com.tanwlanyue.im.packet.JoinGroupResponsePacket;
import com.tanwlanyue.im.util.LogUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author zhanglei211 on 2021/9/1.
 */
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {

    private final LogUtil log = new LogUtil(CreateGroupResponseHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket packet) throws Exception {
        if (packet.getSuccess()) {
            log.info("加入group id" + packet.getGroupId() + "成功");
        } else {
            log.info("加入group id" + packet.getGroupId() + "失败，原因：" + packet.getReason());
        }
    }
}
