package com.xslgy.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : 玄机
 * @Data : 2021/1/16  下午9:05
 * @Description : 社会组织查询实体
 */
@Data
public class SearchSocietyOrgDTO {

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("组织名称")
    private String orgName;

    @ApiModelProperty("组织地址")
    private String orgAddress;

    @ApiModelProperty("组织所在城市")
    private String city;

    @ApiModelProperty("组织所在社区")
    private String community;

    @ApiModelProperty("组织分类")
    private String category;

    @ApiModelProperty("法人")
    private String juridicalPerson;

    @ApiModelProperty("联系人")
    private String linkman;
}
