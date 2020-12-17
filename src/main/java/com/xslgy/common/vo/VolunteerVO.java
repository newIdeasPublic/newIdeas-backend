package com.xslgy.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @Author : ZhaoHP
 * @Data : 2020/12/17  下午9:53
 * @Description : 志愿者视图实体
 */
@Data
@ApiModel("志愿者实体")
public class VolunteerVO {

    @NotNull(message = "姓名不能为空")
    @ApiModelProperty("姓名")
    private String name;

    @NotNull(message = "性别不能为空")
    @ApiModelProperty("性别")
    private String sex;

    @NotNull(message = "出生日期不能为空")
    @ApiModelProperty("出生日期")
    private Date birthdate;

    @NotNull(message = "手机号不能为空")
    @ApiModelProperty("手机号")
    private String mobile;

    @NotNull(message = "专业不能为空")
    @ApiModelProperty("专业")
    private String professional;

    @NotNull(message = "学历不能为空")
    @ApiModelProperty("学历")
    private String education;

    @NotNull(message = "身份证号不能为空")
    @ApiModelProperty("身份证号")
    private String identifier;

    @NotNull(message = "户籍不能为空")
    @ApiModelProperty("户籍")
    private String censusRegister;

    @NotNull(message = "照片不能为空")
    @ApiModelProperty("照片地址")
    private String photoUrl;

    @ApiModelProperty("民族")
    private String nation;
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
    @ApiModelProperty("家庭地址")
    private String homeAddr;
    @ApiModelProperty("辖区团队")
    private String areaTeam;
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
