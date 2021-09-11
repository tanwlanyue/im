package com.tanwlanyue.im.packet;

import java.util.List;

import lombok.Data;

/**
 * @author zhanglei211 on 2021/9/1.
 */
@Data
public class CreateGroupResponsePacket extends Packet {

    private List<String> usernameList;

    private String groupId;

    private Boolean success;

    @Override
    public Byte getCommandType() {
        return CommandType.CREATE_GROUP_RESPONSE;
    }
}
