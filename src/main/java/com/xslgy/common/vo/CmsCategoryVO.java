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
    @ApiModelProperty("父级id")
    private Long parentId;
    @ApiModelProperty("图片地址")
    private String imgUrl;
    @ApiModelProperty("排序号")
    private Integer orderNo;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("在菜单中是否显示：1是，0否")
    private Integer isShow;
}
