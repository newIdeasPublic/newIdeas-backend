package com.xslgy.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @Author : ZhaoHP
 * @Data : 2020/12/17  下午9:53
 * @Description : 志愿者视图实体
 */
@Data
@ApiModel("志愿者实体")
@Accessors(chain = true)
public class VolunteerVO {

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("出生日期")
    private Date birthdate;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("专业")
    private String professional;

    @ApiModelProperty("学历")
    private String education;

    @ApiModelProperty("身份证号")
    private String idCard;

    @ApiModelProperty("户籍")
    private String censusRegister;

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
