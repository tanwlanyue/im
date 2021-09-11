package com.tanwlanyue.im.console;

import java.util.HashMap;
import java.util.Scanner;

import com.google.common.collect.Maps;
import com.tanwlanyue.im.constant.ActionEnum;
import com.tanwlanyue.im.util.LogUtil;
import com.tanwlanyue.im.util.SessionUtil;

import io.netty.channel.Channel;

/**
 * @author zhanglei211 on 2021/9/1.
 */
public class ConsoleCommandManager implements ConsoleCommand {

    private final LogUtil log = new LogUtil(ConsoleCommandManager.class);

    private final HashMap<Integer, ConsoleCommand> ACTION_MAP;

    private final LoginConsoleCommand loginConsoleCommand;

    public ConsoleCommandManager() {
        ACTION_MAP = Maps.newHashMap();
        ACTION_MAP.put(ActionEnum.SEND_TO_USER.getCode(), new MessageConsoleCommand());
        ACTION_MAP.put(ActionEnum.LOGOUT.getCode(), new LogoutConsoleCommand());
        ACTION_MAP.put(ActionEnum.CREATE_GROUP.getCode(), new CreateGroupConsoleCommand());
        ACTION_MAP.put(ActionEnum.JOIN_GROUP.getCode(), new JoinGroupConsoleCommand());
        ACTION_MAP.put(ActionEnum.QUIT_GROUP.getCode(), new QuitGroupConsoleCommand());
        ACTION_MAP.put(ActionEnum.LIST_GROUP_MEMBER.getCode(), new ListGroupMemberConsoleCommand());
        ACTION_MAP.put(ActionEnum.SEND_TO_GROUP.getCode(), new MessageGroupConsoleCommand());

        loginConsoleCommand=new LoginConsoleCommand();
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        if(!SessionUtil.hasLogin(channel)){
            loginConsoleCommand.exec(scanner,channel);
        }
        System.out.println("选择1.发信息 2.注销 3.建群 4.加群 5.退群 6.查看群成员 7. 发群消息");
        String action = scanner.nextLine();
        if("".equals(action)){
            return;
        }
        int select = Integer.parseInt(action);
        ConsoleCommand consoleCommand = ACTION_MAP.get(select);
        if (consoleCommand != null) {
            consoleCommand.exec(scanner, channel);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            log.error("识别不了[" + select + "]命令");
        }
    }
}
