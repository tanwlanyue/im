package com.tanwlanyue.im.packet;

import lombok.Data;

/**
 * @author zhanglei211 on 2021/9/1.
 */
@Data
public class LogoutResponsePacket extends Packet{

    private Boolean success;

    @Override
    public Byte getCommandType() {
        return CommandType.LOGOUT_RESPONSE;
    }
}
