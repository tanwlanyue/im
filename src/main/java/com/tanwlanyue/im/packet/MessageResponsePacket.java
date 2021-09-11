package com.tanwlanyue.im.packet;

import lombok.Data;

/**
 * @author zhanglei211 on 2021/8/30.
 */
@Data
public class MessageResponsePacket extends Packet {

    private String fromUserId;

    private String fromUserName;

    private String message = "server has got message";

    @Override
    public Byte getCommandType() {
        return CommandType.MESSAGE_RESPONSE;
    }
}
