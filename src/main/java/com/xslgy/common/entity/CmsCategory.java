package com.xslgy.common.entity;

import com.xslgy.core.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "cms_category")
public class CmsCategory extends BaseEntity {

    /**
     * 编码
     */
    private String code;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 父级id
     */
    private Long parentId;
    /**
     * 图片地址
     */
    private String imgUrl;
    /**
     * 排序
     */
    private Integer orerNo;
    /**
     * 描述
     */
    private String remark;
}
