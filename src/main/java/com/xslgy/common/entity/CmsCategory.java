package com.xslgy.common.entity;

import com.xslgy.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@ApiModel("cms分类")
@Table(name = "cms_category")
public class CmsCategory extends BaseEntity {

    @ApiModelProperty("分类编码")
    private String code;
    @ApiModelProperty("分类名称")
    private String name;
    @ApiModelProperty("父级id")
    private Long parentId;
    @ApiModelProperty("图片地址")
    private String imgUrl;
    @ApiModelProperty("排序号")
    private Integer orerNo;
    @ApiModelProperty("备注")
    private String remark;
}
