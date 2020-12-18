package com.xslgy.modules.api.action;

import com.xslgy.common.entity.Volunteer;
import com.xslgy.common.service.VolunteerService;
import com.xslgy.common.vo.VolunteerVO;
import com.xslgy.core.action.BaseController;
import com.xslgy.core.utils.Result;
import com.xslgy.core.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    public Result<String> addVolunteer(@Valid @NotNull @RequestBody VolunteerVO volunteerVO) {
        log.info("调用【上传志愿者档案】接口：{}", volunteerVO);
        volunteerService.addVolunteer(volunteerVO);
        return ResultUtils.success("添加成功");
    }

    @ApiOperation("更新志愿者档案")
    @PostMapping("/update")
    public Result<String> updateVolunteer(@RequestBody VolunteerVO volunteerVO) {
        log.info("调用【更新志愿者档案】接口：{}", volunteerVO);
        volunteerService.updateVolunteer(volunteerVO);
        return ResultUtils.success("更新成功");
    }

    @ApiOperation("分页查询志愿者列表")
    @GetMapping("/list/page")
    public Result<List<Volunteer>> getVolunteerPage() {

        return null;
    }

}
