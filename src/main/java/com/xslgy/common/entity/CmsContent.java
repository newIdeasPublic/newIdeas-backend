package com.xslgy.common.entity;

import com.xslgy.core.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "cms_content")
@ToString
public class CmsContent extends BaseEntity {

    private Long categoryId;
    private String title;
    private String shortContent;
    private String content;
    private String imgUrl;
    private String imgLink;
    private String shortImg;
    private Date time;
    private Integer orderNo;
    @ApiModelProperty(value = "状态：0草稿，1已发布，2已撤销")
    private Integer status;
}
