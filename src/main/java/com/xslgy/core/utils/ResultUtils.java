package com.xslgy.core.utils;

public class ResultUtils {

    private final static int SUCCESS_CODE = 200;
    private final static String SUCCESS_MESSAGE = "成功";

    private final static int ERROR_CODE = 500;
    private final static String ERROR_MESSAGE = "失败";

    public static Result success(Object data) {
        return new Result(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(int code, String message) {
        return new Result(code, message);
    }

    public static Result error(String message) {
        return new Result(ERROR_CODE, message);
    }
}
