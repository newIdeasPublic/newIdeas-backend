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
@ApiModel("系统配置")
@Table(name = "sys_config")
public class SysConfig extends BaseEntity {

    @ApiModelProperty("编码")
    private String code;
    @ApiModelProperty("配置名称")
    private String name;
    @ApiModelProperty("配置值")
    @Column(name = "value", length = 2048)
    private String value;
    @ApiModelProperty("备注")
    private String remark;
}
