package com.xslgy.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author : ZhaoHP
 * @Data : 2020/12/20  上午11:42
 * @Description : 志愿者查询条件封装的实体
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SearchVolunteerDTO extends BaseVolunteerDTO{

    @ApiModelProperty("技能类别")
    private String skillCategory;
}
