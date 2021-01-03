package com.xslgy.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("会员登录信息")
public class MemberLoginVO {
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("手机号，用户名和手机号不能同时为空")
    private String mobile;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("验证码")
    private String code;
    @ApiModelProperty("验证码编号")
    private String uuid;
}
