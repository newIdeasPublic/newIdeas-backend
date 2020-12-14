package com.xslgy.core.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "返回结果")
public class Result<T> {
    /**
     * 错误码
     */
    @ApiModelProperty(value = "状态码：200正常，非200为异常")
    private int code;
    /**
     * 消息
     */
    @ApiModelProperty(value = "返回消息")
    private String message;
    /**
     * 数据
     */
    @ApiModelProperty(value = "返回数据")
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
