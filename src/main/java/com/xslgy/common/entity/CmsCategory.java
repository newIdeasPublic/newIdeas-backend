package com.xslgy.common.entity;

import com.xslgy.core.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "cms_category")
public class CmsCategory extends BaseEntity {

    private String code;
    private String name;
    private String imgUrl;
    private String description;
}
