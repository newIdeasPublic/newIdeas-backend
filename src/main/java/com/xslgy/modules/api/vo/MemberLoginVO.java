package com.xslgy.modules.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("前台用户登录接口")
public class MemberLoginVO {

    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("手机号")
    private String mobile;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("验证码")
    private String code;
    @ApiModelProperty("验证码编号")
    private String uuid;
}
