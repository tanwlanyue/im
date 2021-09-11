package com.tanwlanyue.im.packet;

import lombok.Data;

import java.util.List;

/**
 * @author zhanglei211 on 2021/9/1.
 */
@Data
public class CreateGroupRequestPacket extends Packet {

    private String createUserid;

    private List<String> includeUseridList;

    @Override
    public Byte getCommandType() {
        return CommandType.CREATE_GROUP_REQUEST;
    }
}
