package com.xslgy.common.utils;

public class Constant {

    /**
     * 隐私数据加密用的公钥
     */
    public final static String RSA_PUBLIC_KEY_CODE = "rsa_public_key";
    /**
     * 阿里大鱼短信配置信息
     */
    public final static String ALI_SMS_ACCOUNT = "ali_sms_account";

    /**
     * 个人、个体工商、企业范围枚举
     */
    public enum FormScope {
        /**
         * 个人
         */
        PERSONAL(1),
        /**
         * 个体工商
         */
        INDIVIDUAL_BUSINESS(2),
        /**
         * 企业
         */
        COMPANY(3);

        private int val;
        public int getValue() {
            return val;
        }
        FormScope(int value) {
            this.val = value;
        }
    }

    /**
     * 会员状态：0启用，1禁用
     */
    public enum MemberStatus {
        /**
         * 启用
         */
        ENABLED(0),
        /**
         * 禁用
         */
        DISABLED(1);
        private int val;
        public int getValue() {
            return val;
        }
        MemberStatus(int value) {
            this.val = value;
        }
    }

    /**
     * 是否
     */
    public enum SF {
        /**
         * 是
         */
        YES(1),
        /**
         * 否
         */
        NO(0);
        private int val;
        public int getValue() {
            return val;
        }
        SF(int value) {
            this.val = value;
        }
    }

    /**
     * 短信类型
     */
    public enum SMS_TYPE {
        /**
         * 验证码短信
         */
        VERIFICATE_CODE(1, "login_sms");
        private int val;
        private String code;
        public int getValue() {
            return val;
        }
        public String getCode() {
            return code;
        }
        SMS_TYPE(int value, String code) {
            this.val = value;
            this.code = code;
        }
    }

    /**
     * 审核状态
     */
    public enum CHECK_STATUS {
        /**
         * 未审核
         */
        NOT_CHECK(1),
        /**
         * 审核中
         */
        CHECKING(2),
        /**
         * 审核通过
         */
        CHECK_PASS(3),
        /**
         * 审核不通过
         */
        CHECK_NOTPASS(4);
        private int status;
        public int getStatus() {
            return status;
        }
        CHECK_STATUS(int check) {
            this.status = check;
        }
    }
}
