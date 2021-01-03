package com.xslgy.modules.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("短信发送参数集")
public class SendSMSVO {
    @ApiModelProperty("手机号")
    private String mobile;
    @ApiModelProperty("图形验证码的uuid")
    private String uuid;
    @ApiModelProperty("图形验证码")
    private String code;
}
