package com.xslgy.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author : 玄机
 * @Data : 2021/1/16  下午9:13
 * @Description : 简易社会组织视图实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SocietyOrgVO {
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("组织名称")
    private String orgName;

    @ApiModelProperty("联系电话")
    private String mobile;

    @ApiModelProperty("组织地址")
    private String orgAddress;

    @ApiModelProperty("组织邮箱")
    private String orgMail;

    @ApiModelProperty("组织介绍")
    private String orgIntroduction;

    @ApiModelProperty("资质证书")
    private String orgCertificationUrl;

    @ApiModelProperty("组织Logo")
    private String orgLogoUrl;

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

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("联系人")
    private String linkman;

    @ApiModelProperty("联系人电话")
    private String linkmanMobile;

    @ApiModelProperty("联系人介绍")
    private String linkmanIntroduction;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建时间")
    private Date createdTime;

    @ApiModelProperty("更新时间")
    private Date updatedTime;
}
