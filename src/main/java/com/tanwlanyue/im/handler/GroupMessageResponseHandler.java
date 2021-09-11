package com.tanwlanyue.im.handler;

import com.tanwlanyue.im.packet.GroupMessageResponsePacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author zhanglei211 on 2021/9/1.
 */
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket packet) throws Exception {
        System.out.println(packet.getFromUser()+"：say"+packet.getMessage());
    }
}
