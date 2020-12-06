package com.xslgy.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("cms分类")
public class CmsCategoryVO {
    @ApiModelProperty("cms分类Id")
    private Long id;
    @ApiModelProperty("cms分类名称")
    private String name;
    @ApiModelProperty("cms分类编码")
    private String code;
    @ApiModelProperty("cms分类描述")
    private String description;
}
