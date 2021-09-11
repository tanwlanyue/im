package com.tanwlanyue.im.serialize;

/**
 * @author zhanglei211 on 2021/8/29.
 */
public interface Serializer {

    Serializer DEFAULT = new JsonSerializer();

    /**
     * 序列化类型
     * 
     * @return
     */
    byte getSerializerType();

    /**
     * 序列化
     * 
     * @param object
     * @return
     */
    byte[] serialize(Object object);

    /**
     * 反序列化
     * 
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
