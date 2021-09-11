package com.tanwlanyue.im.packet;

import lombok.Data;

/**
 * @author zhanglei211 on 2021/9/1.
 */
@Data
public class QuitGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommandType() {
        return CommandType.QUIT_GROUP_REQUEST;
    }
}
