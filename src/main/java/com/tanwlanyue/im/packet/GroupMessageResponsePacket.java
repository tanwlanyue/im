package com.tanwlanyue.im.packet;

import com.tanwlanyue.im.util.Session;
import lombok.Data;

/**
 * @author zhanglei211 on 2021/9/1.
 */
@Data
public class GroupMessageResponsePacket extends Packet{

    private String groupId;

    private String message;

    private Session fromUser;

    @Override
    public Byte getCommandType() {
        return CommandType.GROUP_MESSAGE_RESPONSE;
    }
}
