package com.xslgy.modules.admin.utils;

import org.apache.tomcat.util.security.MD5Encoder;

import java.nio.charset.StandardCharsets;

public class PasswordUtils {
    public static String encode(String password, String salt) {
        return MD5Encoder.encode((password + "|" +salt).getBytes(StandardCharsets.UTF_8));
    }
}
