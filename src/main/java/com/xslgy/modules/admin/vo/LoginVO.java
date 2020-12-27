package com.xslgy.modules.admin.vo;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("登录参数")
public class LoginVO {
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    @NotNull
    private String password;
    @ApiModelProperty("验证码")
    @NotNull
    private String code;
    @ApiModelProperty("手机号（用户名和手机号填写一个就行）")
    private String mobile;
    @ApiModelProperty("图形验证码的uuid")
    @NotNull
    private String uuid;
}
