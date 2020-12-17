package com.xslgy.modules.api.action;

import com.xslgy.common.service.VolunteerService;
import com.xslgy.common.vo.VolunteerVO;
import com.xslgy.core.action.BaseController;
import com.xslgy.core.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * @Author : ZhaoHP
 * @Data : 2020/12/17  下午9:47
 * @Description : 志愿者相关控制层接口
 */
@Slf4j
@Api(tags = {"志愿者相关接口"})
@RestController
@Validated
@RequestMapping("/api/volunteer")
public class VolunteerController extends BaseController {

    @Resource
    private VolunteerService volunteerService;

    @ApiOperation("上传志愿者档案")
    @PostMapping("/add")
    public Result<String> addVolunteer(@RequestBody @NotNull VolunteerVO volunteerVO){
        log.info("调用【上传志愿者档案】接口：{}", volunteerVO);
        return new Result<String>("添加成功");
    }
}
