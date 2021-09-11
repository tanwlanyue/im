package com.tanwlanyue.im.util;

import java.time.LocalDateTime;

/**
 * @author zhanglei211 on 2021/9/1.
 */
public class LogUtil {

    private String className;

    public LogUtil(Class clazz) {
        className = clazz.getName();
    }

    public void info(String str) {
        System.out.println(LocalDateTime.now() + " " + className + " --------------- [ " + str+" ]");
    }

    public void error(String str) {
        System.err.println(LocalDateTime.now() + " " + className + " --------------- [ " + str+" ]");
    }
}
