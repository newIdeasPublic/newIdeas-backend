package com.xslgy.modules.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("前端用户注册表单信息")
public class MemberRegistVO {
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("手机号")
    private String mobile;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("短信验证码")
    private String code;
}
