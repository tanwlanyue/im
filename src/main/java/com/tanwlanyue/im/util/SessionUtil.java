package com.tanwlanyue.im.util;

import com.google.common.collect.Lists;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.util.AttributeKey;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhanglei211 on 2021/8/31.
 */
public class SessionUtil {

    private static final AttributeKey<Session> SESSION = AttributeKey.newInstance("session");

    private static final ConcurrentHashMap<String, Channel> USERID_CHANNEL_MAP = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<String, ChannelGroup> GROUP_ID_CHANNEL_GROUP_MAP = new ConcurrentHashMap<>();

    public static void bind(Session session, Channel channel) {
        USERID_CHANNEL_MAP.put(session.getUserid(), channel);
        channel.attr(SESSION).set(session);
    }

    public static void unbind(Channel channel) {
        if (hasLogin(channel)) {
            USERID_CHANNEL_MAP.remove(getSession(channel).getUserid());
            clearSession(channel);
        }
    }

    public static Session getSession(Channel channel) {
        return channel.attr(SESSION).get();
    }

    public static Channel getChannel(String userid) {
        return USERID_CHANNEL_MAP.get(userid);
    }

    public static Boolean hasLogin(Channel channel) {
        return getSession(channel) != null;
    }

    public static void clearSession(Channel channel) {
        channel.attr(SESSION).set(null);
    }

    public static void bindChannelGroup(String groupId, ChannelGroup channelGroup) {
        GROUP_ID_CHANNEL_GROUP_MAP.put(groupId, channelGroup);
    }

    public static ChannelGroup getChannelGroup(String groupId) {
        return GROUP_ID_CHANNEL_GROUP_MAP.get(groupId);
    }

    public static List<Session> getSessionList(String groupId){
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        List<Session> sessionList = Lists.newArrayList();
        for (Channel channel : channelGroup) {
            Session session = SessionUtil.getSession(channel);
            sessionList.add(session);
        }
        return sessionList;
    }

    public static void removeFromGroup(String groupId,Channel channel){
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.remove(channel);
    }
}
