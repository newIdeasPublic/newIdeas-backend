package com.xslgy.modules.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("系统参数配置")
public class SysConfigVO {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("编码")
    private String code;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("值")
    private String value;
    @ApiModelProperty("备注")
    private String remark;
}
