package com.tanwlanyue.im.handler;

import com.tanwlanyue.im.packet.LoginResponsePacket;
import com.tanwlanyue.im.util.LogUtil;
import com.tanwlanyue.im.util.Session;
import com.tanwlanyue.im.util.SessionUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author zhanglei211 on 2021/8/31.
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    private final LogUtil log = new LogUtil(LoginResponseHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket packet) throws Exception {
        if (packet.getSuccess()) {
            SessionUtil.bind(new Session(packet.getUserid(), packet.getUsername()), ctx.channel());
            log.info("userid: " + packet.getUserid() + " username:" + packet.getUsername() + " 登录成功");
        } else {
            log.info("userid: " + packet.getUserid() + " username:" + packet.getUsername() + " 登录失败，reason:"
                    + packet.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.error("客户端被关闭");
    }
}
