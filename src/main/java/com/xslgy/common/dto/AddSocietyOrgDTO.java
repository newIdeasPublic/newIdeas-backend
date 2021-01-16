package com.xslgy.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author : 玄机
 * @Data : 2021/1/16  下午8:23
 * @Description : 添加社会组织DTO
 */
@Data
public class AddSocietyOrgDTO{

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("组织名称")
    @NotNull(message = "组织名称不能为空")
    private String orgName;

    @ApiModelProperty("联系电话")
    @NotNull(message = "联系电话不能为空")
    private String mobile;

    @ApiModelProperty("组织地址")
    private String orgAddress;

    @ApiModelProperty("组织邮箱")
    private String orgMail;

    @ApiModelProperty("组织资质证书地址")
    @NotNull(message = "组织资质证书不能为空")
    private String orgCertificationUrl;

    @ApiModelProperty("组织Logo地址")
    private String orgLogoUrl;

    @ApiModelProperty("组织介绍")
    private String orgIntroduction;

    @ApiModelProperty("组织所在城市")
    private String city;

    @ApiModelProperty("组织所在社区")
    private String community;

    @ApiModelProperty("组织分类")
    private String category;

    @ApiModelProperty("法人")
    private String juridicalPerson;

    @ApiModelProperty("法人介绍")
    private String juridicalPersonIntroduction;

    @ApiModelProperty("联系人")
    @NotNull(message = "联系人不能为空")
    private String linkman;

    @ApiModelProperty("联系人电话")
    @NotNull(message = "联系人电话不能为空")
    private String linkmanMobile;

    @ApiModelProperty("联系人介绍")
    private String linkmanIntroduction;

    @ApiModelProperty("备注")
    private String remark;
}
