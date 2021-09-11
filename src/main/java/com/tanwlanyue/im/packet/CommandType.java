package com.tanwlanyue.im.packet;

/**
 * @author zhanglei211 on 2021/8/29.
 */
public interface CommandType {
    Byte LOGIN_REQUEST = 1;
    Byte LOGIN_RESPONSE = 2;
    Byte MESSAGE_REQUEST = 3;
    Byte MESSAGE_RESPONSE = 4;
    Byte CREATE_GROUP_REQUEST = 5;
    Byte CREATE_GROUP_RESPONSE = 6;
    Byte GROUP_MESSAGE_REQUEST = 7;
    Byte GROUP_MESSAGE_RESPONSE = 8;
    Byte LOGOUT_REQUEST = 9;
    Byte LOGOUT_RESPONSE = 10;
    Byte JOIN_GROUP_REQUEST = 11;
    Byte JOIN_GROUP_RESPONSE = 12;
    Byte QUIT_GROUP_REQUEST = 13;
    Byte QUIT_GROUP_RESPONSE = 14;
    Byte LIST_GROUP_MEMBER_REQUEST = 15;
    Byte LIST_GROUP_MEMBER_RESPONSE = 16;
}
