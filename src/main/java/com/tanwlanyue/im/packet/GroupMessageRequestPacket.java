package com.tanwlanyue.im.packet;

import lombok.Data;

/**
 * @author zhanglei211 on 2021/9/1.
 */
@Data
public class GroupMessageRequestPacket extends Packet{

    private String toGroupId;

    private String message;

    @Override
    public Byte getCommandType() {
        return CommandType.GROUP_MESSAGE_REQUEST;
    }
}
