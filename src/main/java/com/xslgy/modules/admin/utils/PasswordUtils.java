package com.xslgy.modules.admin.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {
    public static String encode(String password, String salt) {
        StringBuffer hex = new StringBuffer();
        try {
            byte[] hash = MessageDigest.getInstance("MD5").digest((password + "|" + salt).getBytes(StandardCharsets.UTF_8));
            hex = new StringBuffer(hash.length * 2);
            for (byte b : hash) {
                if ((b & 0xFF) < 0x10) {
                    hex.append("0");
                }
                hex.append(Integer.toHexString(b & 0xFF));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hex.toString();
    }
}
