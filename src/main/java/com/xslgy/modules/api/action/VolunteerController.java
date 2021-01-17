package com.xslgy.modules.api.action;

import com.xslgy.common.dto.AddVolunteerDTO;
import com.xslgy.common.dto.SearchVolunteerDTO;
import com.xslgy.common.service.VolunteerService;
import com.xslgy.common.utils.PageUtils;
import com.xslgy.common.vo.VolunteerVO;
import com.xslgy.core.action.BaseController;
import com.xslgy.core.utils.Result;
import com.xslgy.core.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @Author : ZhaoHP
 * @Data : 2020/12/17  下午9:47
 * @Description : 志愿者相关控制层接口
 */
@Slf4j
@Validated
@RestController
@Api(tags = {"志愿者相关接口"})
@RequestMapping("/api/volunteer")
public class VolunteerController extends BaseController {

    @Resource
    private VolunteerService volunteerService;

    @ApiOperation("上传志愿者档案")
    @PostMapping("/add")
    public Result<String> addVolunteer(@Valid @NotNull @RequestBody AddVolunteerDTO addVolunteerDTO) {
        log.info("调用【上传志愿者档案】接口：{}", addVolunteerDTO);
        volunteerService.addVolunteer(addVolunteerDTO);
        return ResultUtils.success("添加成功");
    }

    @ApiOperation("更新志愿者档案")
    @PostMapping("/update")
    public Result<String> updateVolunteer(@RequestBody AddVolunteerDTO addVolunteerDTO) {
        log.info("调用【更新志愿者档案】接口：{}", addVolunteerDTO);
        volunteerService.updateVolunteer(addVolunteerDTO);
        return ResultUtils.success("更新成功");
    }

    @ApiOperation("分页查询志愿者列表")
    @PostMapping("/list/page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页显示的记录数", defaultValue = "10"),
            @ApiImplicitParam(name = "pageNum", value = "当前页数", defaultValue = "1")
    })
    public Result<PageUtils> getVolunteerPage(
            @RequestBody SearchVolunteerDTO searchVolunteerDTO,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        log.info("调用【分页查询志愿者列表】接口: {}；pageNum:{}；pageSize:{}", searchVolunteerDTO, pageNum, pageSize);
        return ResultUtils.success(volunteerService.getVolunteerPage(searchVolunteerDTO, pageNum, pageSize));
    }

    @ApiOperation("根据主键ID删除对应的档案")
    @DeleteMapping("/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = true)
    })
    public Result<String> deleteById(@RequestParam("id") @NotNull Long id) {
        log.info("调用【根据主键ID删除对应的档案】接口；{}", id);
        return ResultUtils.success(volunteerService.deleteById(id));
    }

    @ApiOperation("根据ID查询详情信息")
    @GetMapping("/getDetailInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", required = true)
    })
    public Result<VolunteerVO> getVolunteerVoById(@RequestParam("id") @NotNull Long id) {
        log.info("调用【根据ID查询详情信息】接口；{}", id);
        return ResultUtils.success(volunteerService.getVolunteerVoById(id));
    }
}
