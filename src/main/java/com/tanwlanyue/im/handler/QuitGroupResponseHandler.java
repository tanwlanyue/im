package com.tanwlanyue.im.handler;

import com.tanwlanyue.im.packet.QuitGroupResponsePacket;
import com.tanwlanyue.im.util.LogUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author zhanglei211 on 2021/9/1.
 */
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {

    private final LogUtil log = new LogUtil(MessageResponseHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket packet) throws Exception {
        if (packet.getSuccess()) {
            log.info("退出群聊group id:" + packet.getGroupId() + "成功");
        } else {
            log.info("退出群聊group id:" + packet.getGroupId() + "失败");
        }
    }
}
