package com.tanwlanyue.im.packet;

import lombok.Data;

/**
 * @author zhanglei211 on 2021/8/30.
 */
@Data
public class MessageRequestPacket extends Packet{
    private String fromUserid;
    private String toUserid;
    private String message;
    @Override
    public Byte getCommandType() {
        return CommandType.MESSAGE_REQUEST;
    }
}
