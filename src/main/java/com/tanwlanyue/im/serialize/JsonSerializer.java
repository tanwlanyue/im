package com.tanwlanyue.im.serialize;

import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;

/**
 * @author zhanglei211 on 2021/8/29.
 */
public class JsonSerializer implements Serializer{

    @Override
    public byte getSerializerType() {
        return SerializerType.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return new Gson().toJson(object).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return new Gson().fromJson(new String(bytes),clazz);
    }
}
