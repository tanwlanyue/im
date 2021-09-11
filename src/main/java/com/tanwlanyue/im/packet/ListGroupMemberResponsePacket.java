package com.tanwlanyue.im.packet;

import java.util.List;

import com.tanwlanyue.im.util.Session;

import lombok.Data;

/**
 * @author zhanglei211 on 2021/9/1.
 */
@Data
public class ListGroupMemberResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommandType() {
        return CommandType.LIST_GROUP_MEMBER_RESPONSE;
    }
}
