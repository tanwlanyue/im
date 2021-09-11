package com.tanwlanyue.im.console;

import java.util.Scanner;

import io.netty.channel.Channel;

/**
 * @author zhanglei211 on 2021/9/1.
 */
public interface ConsoleCommand {
    /**
     * 控制台操作
     * @param scanner
     * @param channel
     */
    void exec(Scanner scanner, Channel channel);
}
