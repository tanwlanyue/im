package com.tanwlanyue.im.constant;

/**
 * @author zhanglei211 on 2021/9/1.
 */
public enum ActionEnum {
    SEND_TO_USER(1),
    LOGOUT(2),
    CREATE_GROUP(3),
    JOIN_GROUP(4),
    QUIT_GROUP(5),
    LIST_GROUP_MEMBER(6),
    SEND_TO_GROUP(7),;

    /**
     * 操作码
     */
    private int code;

    ActionEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
