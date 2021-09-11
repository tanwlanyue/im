package com.tanwlanyue.im.packet;

import lombok.Data;

/**
 * @author zhanglei211 on 2021/9/1.
 */
@Data
public class JoinGroupResponsePacket extends Packet {

    private String groupId;

    private Boolean success;

    private String reason;

    @Override
    public Byte getCommandType() {
        return CommandType.JOIN_GROUP_RESPONSE;
    }
}
