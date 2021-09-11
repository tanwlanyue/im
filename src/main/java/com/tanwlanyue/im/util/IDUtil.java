package com.tanwlanyue.im.util;

import java.util.UUID;

/**
 * @author zhanglei211 on 2021/9/1.
 */
public class IDUtil {

    public static String randomId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
