package com.xslgy.common.utils;

import java.util.regex.Pattern;

/**
 * 正则校验工具类
 * @author lamdaer
 * @createTime 2020/12/19
 */
public class RegexUtils {
    /**
     * 校验手机号，支持以下号段
     * 中国电信: 133、153、173、177、180、181、189、191、193、199
     * 中国联通: 130、131、132、155、156、166、175、176、185、186、166
     * 中国移动: 134、135、136、137、138、139、147、150、151、152、157、158、159、172、178、182、183、184、187、188、198
     * @param phoneNumber 待校验的手机号
     * @return 校验成功返回true，校验失败返回false
     */
    public static boolean checkMobile(String phoneNumber) {
        String regex = "^1[3-9]\\d{9}$";
        return Pattern.matches(regex, phoneNumber);
    }
}
