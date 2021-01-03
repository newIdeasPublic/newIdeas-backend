package com.xslgy.modules.admin.utils;

import com.xslgy.common.utils.MD5Utils;

public class PasswordUtils {
    public static String encode(String password, String salt) {
        return MD5Utils.encrypt(password + "|" + salt);
    }
}
