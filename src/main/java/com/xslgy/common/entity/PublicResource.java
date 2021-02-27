package com.xslgy.common.entity;

import com.xslgy.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@ApiModel("公共资源")
@Table(name = "public_resource")
public class PublicResource extends BaseEntity {

    @ApiModelProperty("资源名称")
    private String name;
    @ApiModelProperty("一级分类")
    private String levelOne;
    @ApiModelProperty("二级分类")
    private String levelTwo;
    @ApiModelProperty("联系方式")
    private String mobile;
    @Column(name = "content", length = 2048)
    @ApiModelProperty("具体内容")
    private String content;
    @Column(name = "img", length = 4096)
    @ApiModelProperty("图片")
    private String img;
}
