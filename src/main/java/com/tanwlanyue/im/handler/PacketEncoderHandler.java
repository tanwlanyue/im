package com.tanwlanyue.im.handler;

import com.tanwlanyue.im.packet.Packet;
import com.tanwlanyue.im.packet.PacketCodeC;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author zhanglei211 on 2021/8/31.
 */
public class PacketEncoderHandler extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        PacketCodeC.INSTANCE.encode(byteBuf,packet);
    }
}
