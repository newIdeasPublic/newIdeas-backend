package com.xslgy.common.utils;

public class Constant {

    /**
     * 隐私数据加密用的公钥
     */
    public final static String RSA_PUBLIC_KEY_CODE = "rsa_public_key";

    /**
     * 个人、个体工商、企业范围枚举
     */
    public enum FormScope {
        PERSONAL(1),
        INDIVIDUAL_BUSINESS(2),
        COMPANY(3);

        private int val;
        public int getValue() {
            return val;
        }
        FormScope(int value) {
            this.val = value;
        }
    }
}
