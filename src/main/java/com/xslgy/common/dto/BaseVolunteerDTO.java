package com.xslgy.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @Author : ZhaoHP
 * @Data : 2020/12/19  下午2:10
 * @Description : 志愿者接口封装基础实体类
 */
@Data
@Accessors(chain = true)
public class BaseVolunteerDTO {

    @ApiModelProperty("主键ID")
    private Long id;

    @NotNull(message = "姓名不能为空")
    @ApiModelProperty("姓名")
    private String name;

    @NotNull(message = "性别不能为空")
    @ApiModelProperty("性别")
    private String sex;

    @NotNull(message = "手机号不能为空")
    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("民族")
    private String nation;

    @ApiModelProperty("家庭地址")
    private String homeAddr;

    @ApiModelProperty("辖区团队")
    private String areaTeam;
}
