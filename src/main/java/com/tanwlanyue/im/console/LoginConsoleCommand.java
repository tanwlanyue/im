package com.tanwlanyue.im.console;

import java.util.Scanner;

import com.tanwlanyue.im.packet.LoginRequestPacket;

import io.netty.channel.Channel;

/**
 * @author zhanglei211 on 2021/9/1.
 */
public class LoginConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入用户名");
        String username = scanner.nextLine();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUsername(username);
        channel.writeAndFlush(loginRequestPacket);
        try {
            System.out.println("等待响应");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
