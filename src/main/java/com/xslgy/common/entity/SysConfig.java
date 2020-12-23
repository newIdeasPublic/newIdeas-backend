package com.xslgy.common.entity;

import com.xslgy.core.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "sys_config")
public class SysConfig extends BaseEntity {

    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 值
     */
    @Column(name = "value", length = 2048)
    private String value;
    /**
     * 备注
     */
    private String remark;
}
