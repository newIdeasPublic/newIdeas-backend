package com.xslgy.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("会员返回信息")
public class MemberVO {

    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("手机号")
    private String mobile;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("所属范围")
    private Integer scope;
    @ApiModelProperty("状态")
    private Integer status;
}
