package com.tanwlanyue.im.packet;

import com.google.gson.annotations.Expose;
import lombok.Data;

/**
 * @author zhanglei211 on 2021/8/29.
 */
@Data
public abstract class Packet {
    /**
     * 版本号
     */
    @Expose(serialize = false, deserialize = false)
    private Byte version = 1;

    /**
     * 指令
     * 
     * @return
     */
    public abstract Byte getCommandType();
}
