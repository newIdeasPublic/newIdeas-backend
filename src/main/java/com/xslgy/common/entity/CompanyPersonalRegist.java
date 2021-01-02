package com.xslgy.common.entity;

import com.xslgy.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@ApiModel("个人、个体、企业注册登记表")
@Table(name = "form_company_personal")
public class CompanyPersonalRegist extends BaseEntity {

    @ApiModelProperty("联系人姓名,必填")
    private String name;
    @ApiModelProperty("联系人工作单位")
    private String company;
    @ApiModelProperty("联系人性别，必填")
    private Integer gender;
    @ApiModelProperty("联系人头像（根据性别，系统自动选择默认头像）")
    private String headImage;
    @ApiModelProperty("联系人手机，必填")
    private String mobile;
    @ApiModelProperty("联系人微信号")
    private String wechat;
    @ApiModelProperty("email")
    private String email;
    @Column(name = "id_card", length = 1024)
    @ApiModelProperty("联系人身份证号，必填")
    private String idCard;
    @ApiModelProperty("联系人地址")
    private String address;
    @Column(name = "description", length = 1024)
    @ApiModelProperty("联系人介绍")
    private String description;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("企业名称，个体工商/企业必填")
    private String companyName;
    @ApiModelProperty("企业地址")
    private String companyAddress;
    @ApiModelProperty("邮政编码")
    private String postcode;
    @ApiModelProperty("统一社会编码")
    private String companyCode;
    @ApiModelProperty("营业执照，个体工商/企业必填")
    private String businessLicense;
    @ApiModelProperty("企业介绍")
    private String companyDescription;
    @ApiModelProperty("注册资金")
    private BigDecimal registeredFund;
    @ApiModelProperty("企业标志")
    private String companyLogo;
    @ApiModelProperty("联系人职务")
    private String duty;
    @ApiModelProperty("法定代表人姓名，企业必填")
    private String legalPersonName;
    @ApiModelProperty("法定代表人电话")
    private String legalPersonMobile;
    @ApiModelProperty("法人介绍")
    private String legalPersonDescription;
    @ApiModelProperty("所属范围:1个人、2个体工商、3企业")
    private Integer scope;
}
