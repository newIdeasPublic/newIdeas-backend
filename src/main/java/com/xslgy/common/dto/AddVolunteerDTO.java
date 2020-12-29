package com.xslgy.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @Author : ZhaoHP
 * @Data : 2020/12/19  下午2:13
 * @Description : 志愿者添加实体
 */
@Data
@ApiModel("志愿者添加实体")
@EqualsAndHashCode(callSuper = true)
public class AddVolunteerDTO extends BaseVolunteerDTO{

    @NotNull(message = "专业不能为空")
    @ApiModelProperty("专业")
    private String professional;

    @NotNull(message = "学历不能为空")
    @ApiModelProperty("学历")
    private String education;

    @NotNull(message = "出生日期不能为空")
    @ApiModelProperty("出生日期")
    private Date birthdate;

    @NotNull(message = "身份证号不能为空")
    @ApiModelProperty("身份证号")
    private String idCard;

    @NotNull(message = "户籍不能为空")
    @ApiModelProperty("户籍")
    private String censusRegister;

    @NotNull(message = "照片不能为空")
    @ApiModelProperty("照片地址")
    private String photoUrl;

    @ApiModelProperty("血型")
    private String bloodType;

    @ApiModelProperty("学校")
    private String school;

    @ApiModelProperty("政治面貌")
    private String politicsType;

    @ApiModelProperty("工作单位")
    private String workUnit;

    @ApiModelProperty("职务")
    private String position;

    @ApiModelProperty("工作地址")
    private String workAddr;

    @ApiModelProperty("车辆信息")
    private String carInfo;

    @ApiModelProperty("车辆配置")
    private String carConfig;

    @ApiModelProperty("个人技能")
    private List<String> skill;

    @ApiModelProperty("加入慈善的原因")
    private List<String> reason;

    @ApiModelProperty("方便做志愿活动的时间")
    private List<String> freeDate;

    @ApiModelProperty("想要参与的慈善活动")
    private List<String> activity;

    @ApiModelProperty("自我介绍")
    private String introduceMyself;
}
