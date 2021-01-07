package com.xslgy.modules.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("cms内容分类展示/隐藏参数")
public class CategoryShowVO {
    @ApiModelProperty("分类id")
    private Long id;
    @ApiModelProperty("是否展示，1是，0否")
    private Integer isShow;
}
