package com.xslgy.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("cms内容")
public class CmsContentVO {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("分类Id")
    private Long categoryId;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("简介")
    private String shortContent;
    @ApiModelProperty("图片地址")
    private String imgUrl;
    @ApiModelProperty("图片跳转地址")
    private String imgLink;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("发布时间")
    private Date time;
    @ApiModelProperty("序号")
    private Integer orderNo;
    @ApiModelProperty("状态")
    private Integer status;

}
