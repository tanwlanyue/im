package com.tanwlanyue.im.packet;

import lombok.Data;

/**
 * @author zhanglei211 on 2021/8/29.
 */
@Data
public class LoginRequestPacket extends Packet{

    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommandType() {
        return CommandType.LOGIN_REQUEST;
    }
}
