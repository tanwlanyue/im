package com.tanwlanyue.im.packet;

import java.util.Map;

import com.google.common.collect.Maps;
import com.tanwlanyue.im.serialize.JsonSerializer;
import com.tanwlanyue.im.serialize.Serializer;

import io.netty.buffer.ByteBuf;

/**
 * @author zhanglei211 on 2021/8/29.
 */
public enum PacketCodeC {
    /**
     * 单例
     */
    INSTANCE;

    public static final int MAGIC_NUMBER = 0x12345678;

    private static final Map<Byte, Class<? extends Packet>> PACKET_TYPE_MAP;
    private static final Map<Byte, Serializer> SERIALIZER_MAP;

    static {
        PACKET_TYPE_MAP = Maps.newHashMap();
        PACKET_TYPE_MAP.put(CommandType.LOGIN_REQUEST, LoginRequestPacket.class);
        PACKET_TYPE_MAP.put(CommandType.LOGIN_RESPONSE, LoginResponsePacket.class);
        PACKET_TYPE_MAP.put(CommandType.MESSAGE_REQUEST, MessageRequestPacket.class);
        PACKET_TYPE_MAP.put(CommandType.MESSAGE_RESPONSE, MessageResponsePacket.class);
        PACKET_TYPE_MAP.put(CommandType.CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class);
        PACKET_TYPE_MAP.put(CommandType.CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class);
        PACKET_TYPE_MAP.put(CommandType.GROUP_MESSAGE_REQUEST, GroupMessageRequestPacket.class);
        PACKET_TYPE_MAP.put(CommandType.GROUP_MESSAGE_RESPONSE, GroupMessageResponsePacket.class);
        PACKET_TYPE_MAP.put(CommandType.LOGOUT_REQUEST, LogoutRequestPacket.class);
        PACKET_TYPE_MAP.put(CommandType.LOGOUT_RESPONSE, LogoutResponsePacket.class);
        PACKET_TYPE_MAP.put(CommandType.JOIN_GROUP_REQUEST, JoinGroupRequestPacket.class);
        PACKET_TYPE_MAP.put(CommandType.JOIN_GROUP_RESPONSE, JoinGroupResponsePacket.class);
        PACKET_TYPE_MAP.put(CommandType.QUIT_GROUP_REQUEST, QuitGroupRequestPacket.class);
        PACKET_TYPE_MAP.put(CommandType.QUIT_GROUP_RESPONSE, QuitGroupResponsePacket.class);
        PACKET_TYPE_MAP.put(CommandType.LIST_GROUP_MEMBER_REQUEST, ListGroupMemberRequestPacket.class);
        PACKET_TYPE_MAP.put(CommandType.LIST_GROUP_MEMBER_RESPONSE, ListGroupMemberResponsePacket.class);
        SERIALIZER_MAP = Maps.newHashMap();
        Serializer serializer = new JsonSerializer();
        SERIALIZER_MAP.put(serializer.getSerializerType(), serializer);
    }

    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        byte[] data = Serializer.DEFAULT.serialize(packet);
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerType());
        byteBuf.writeByte(packet.getCommandType());
        byteBuf.writeInt(data.length);
        byteBuf.writeBytes(data);
        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf) {
        byteBuf.skipBytes(4);
        byteBuf.skipBytes(1);
        byte serializeType = byteBuf.readByte();
        byte commandType = byteBuf.readByte();
        int length = byteBuf.readInt();
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        Class<? extends Packet> requestType = getRequestType(commandType);
        Serializer serializer = getSerializer(serializeType);
        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }
        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {
        return SERIALIZER_MAP.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {
        return PACKET_TYPE_MAP.get(command);
    }
}
