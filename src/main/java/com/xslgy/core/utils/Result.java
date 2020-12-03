package com.xslgy.core.utils;

import lombok.Data;

@Data
public class Result<T> {
    /**
     * 错误码
     */
    private int code;
    /**
     * 消息
     */
    private String message;
    /**
     * 数据
     */
    private T data;

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public Result(T data) {
        this.data = data;
    }
    public Result() {

    }
}
