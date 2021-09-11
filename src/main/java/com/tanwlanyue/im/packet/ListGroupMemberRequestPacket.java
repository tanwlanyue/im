package com.tanwlanyue.im.packet;

import lombok.Data;

/**
 * @author zhanglei211 on 2021/9/1.
 */
@Data
public class ListGroupMemberRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommandType() {
        return CommandType.LIST_GROUP_MEMBER_REQUEST;
    }
}
