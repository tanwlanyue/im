package com.tanwlanyue.im.handler;

import com.tanwlanyue.im.packet.LoginRequestPacket;
import com.tanwlanyue.im.packet.LoginResponsePacket;
import com.tanwlanyue.im.util.IDUtil;
import com.tanwlanyue.im.util.LogUtil;
import com.tanwlanyue.im.util.Session;
import com.tanwlanyue.im.util.SessionUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * SimpleChannelInboundHandler 自动进行类型判断
 * 
 * @author zhanglei211 on 2021/8/31.
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    private static final LogUtil log=new LogUtil(LoginRequestHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket packet) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        String username = packet.getUsername();
        loginResponsePacket.setUsername(username);
        if (valid(packet)) {
            String userid = IDUtil.randomId();
            loginResponsePacket.setSuccess(true);
            loginResponsePacket.setUserid(userid);
            loginResponsePacket.setUsername(packet.getUsername());
            SessionUtil.bind(new Session(userid,username),ctx.channel());
        } else {
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("invalid account");
            log.error("login失败，username:"+username);
        }
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unbind(ctx.channel());
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
