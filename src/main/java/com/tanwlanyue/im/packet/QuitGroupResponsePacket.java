package com.tanwlanyue.im.packet;

import lombok.Data;

/**
 * @author zhanglei211 on 2021/9/1.
 */
@Data
public class QuitGroupResponsePacket extends Packet {

    private String groupId;

    private Boolean success;

    @Override
    public Byte getCommandType() {
        return CommandType.QUIT_GROUP_RESPONSE;
    }
}
