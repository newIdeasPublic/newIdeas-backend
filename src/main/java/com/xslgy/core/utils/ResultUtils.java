package com.xslgy.core.utils;

public class ResultUtils<T> {

    private final static int SUCCESS_CODE = 200;
    private final static String SUCCESS_MESSAGE = "成功";

    private final static int ERROR_CODE = 500;
    private final static String ERROR_MESSAGE = "失败";

    public static <T> Result<T> success(T t) {
        return new Result<>(SUCCESS_CODE, SUCCESS_MESSAGE, t);
    }

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> error(int code, String message) {
        return new Result<T>(code, message);
    }

    public static <T> Result<T> error(String message) {
        return new Result<T>(ERROR_CODE, message);
    }
}
