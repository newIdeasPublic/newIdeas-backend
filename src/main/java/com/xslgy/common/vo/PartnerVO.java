package com.xslgy.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lamdaer
 * @createTime 2020/12/15
 */
@Data
@ApiModel("伙伴信息")
public class PartnerVO {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("类型")
    private String type;
    @ApiModelProperty("公司名称")
    private String companyName;
    @ApiModelProperty("职位")
    private String position;
    @ApiModelProperty("手机号")
    private String phoneNumber;
    @ApiModelProperty("联系地址")
    private String address;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("备注")
    private String remark;
}
