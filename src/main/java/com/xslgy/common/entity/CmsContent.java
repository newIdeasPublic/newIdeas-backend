package com.xslgy.common.entity;

import com.xslgy.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "cms_content")
@ApiModel("cms内容")
public class CmsContent extends BaseEntity {


    @ApiModelProperty("分类id")
    private Long categoryId;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("内容简介")
    private String shortContent;
    @ApiModelProperty("内容")
    @Column(name = "content", length = 4096)
    private String content;
    @ApiModelProperty("图片地址")
    private String imgUrl;
    @ApiModelProperty("图片跳转地址")
    private String imgLink;
    @ApiModelProperty("缩略图地址")
    private String shortImg;
    @ApiModelProperty("发布时间")
    private Date time;
    @ApiModelProperty("排序")
    private Integer orderNo;
    @ApiModelProperty(value = "状态：0草稿，1已发布，2已撤销")
    private Integer status;
    @ApiModelProperty("备注")
    @Column(name = "remark", length = 4096)
    private String remark;
    @ApiModelProperty("点击量")
    private Integer clickCount;
    @ApiModelProperty("作者")
    private String author;
    @ApiModelProperty("标签，以分号分隔")
    @Column(name = "tags", length = 255)
    private String tags;
}
