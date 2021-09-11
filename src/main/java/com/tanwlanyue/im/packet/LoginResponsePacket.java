package com.tanwlanyue.im.packet;

import lombok.Data;

/**
 * @author zhanglei211 on 2021/8/30.
 */
@Data
public class LoginResponsePacket extends Packet {

    private Boolean success;
    private String reason;
    private String userid;
    private String username;


    @Override
    public Byte getCommandType() {
        return CommandType.LOGIN_RESPONSE;
    }
}
