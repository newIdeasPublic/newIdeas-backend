package com.xslgy.common.entity;

import com.xslgy.core.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author lamdaer
 * @createTime 2020/12/15
 */
@Data
@Entity
@Table(name = "partner")
public class Partner extends BaseEntity {
    @ApiModelProperty("伙伴名称")
    @NotNull(message = "伙伴名称不能为空")
    private String name;
    @ApiModelProperty("联系地址")
    private String address;
    @ApiModelProperty("邮政编码")
    private String zipcode;
    @ApiModelProperty("联系电话")
    @NotNull(message = "联系电话不能为空")
    private String mobile;
    @ApiModelProperty("单位标志")
    private String companyName;
    @ApiModelProperty("统一社会代码")
    private String uniformSocialCode;
    @ApiModelProperty("所属行业")
    private String industy;
    @ApiModelProperty("电子邮箱")
    private String email;
    @ApiModelProperty("登记机关")
    private String registractionOrgan;
    @ApiModelProperty("单位介绍")
    @Column(name = "description", length = 2048)
    private String description;
    @ApiModelProperty("业务范围")
    @Column(name = "business_scope", length = 2048)
    private String businessScope;
    @ApiModelProperty("法人姓名")
    private String legalName;
    @ApiModelProperty("法人联系方式")
    private String legalMobile;
    @ApiModelProperty("法人照片")
    private String legalImg;
    @ApiModelProperty("法人介绍")
    private String legalDescription;
    @ApiModelProperty("备注")
    @Column(name = "remark", length = 2048)
    private String remark;
}
