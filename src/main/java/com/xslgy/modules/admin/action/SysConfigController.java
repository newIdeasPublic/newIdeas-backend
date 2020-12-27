package com.xslgy.modules.admin.action;

import com.xslgy.common.entity.SysConfig;
import com.xslgy.common.service.SysConfigService;
import com.xslgy.core.action.BaseController;
import com.xslgy.core.exception.XSLGYException;
import com.xslgy.core.utils.Result;
import com.xslgy.core.utils.ResultUtils;
import com.xslgy.modules.admin.vo.SysConfigVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
@Api("系统参数配置")
@RestController
@RequestMapping("admin/sysconfig")
public class SysConfigController extends BaseController {

    @Resource
    SysConfigService sysConfigService;

    @ApiOperation("列表")
    @GetMapping("list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "参数编码", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "page", value = "当前页码", dataType = "Integer", paramType = "query", required = true),
            @ApiImplicitParam(name = "size", value = "每页记录数", dataType = "Integer", paramType = "query", required = true)
    })
    public Result list(@RequestParam(value = "code", required = false)String code,
                       @RequestParam("page")Integer page, @RequestParam("size")Integer size) {
        return ResultUtils.success(sysConfigService.findByCode(code, PageRequest.of(page - 1, size)));
    }
    @ApiOperation("通过id获取详情")
    @GetMapping("get/{id}")
    @ApiImplicitParam(name = "id", value = "id", dataType = "Long", paramType = "path", required = true)
    public Result getById(@PathVariable("id")Long id) {
        return ResultUtils.success(sysConfigService.getById(id));
    }
    @ApiOperation("插入")
    @PostMapping("insert")
    public Result insert(@RequestBody SysConfigVO sysConfigVO) {
        if (sysConfigVO.getId() != null) {
            throw new XSLGYException("插入数据id不能有值");
        }
        SysConfig sysConfig = new SysConfig();
        BeanUtils.copyProperties(sysConfigVO, sysConfig);
        return ResultUtils.success(sysConfigService.save(sysConfig));
    }
    @ApiOperation("更新")
    @PostMapping("update")
    public Result update(@RequestBody SysConfigVO sysConfigVO) {
        if (sysConfigVO.getId() == null) {
            throw new XSLGYException("更新数据id不能为空");
        }
        SysConfig sysConfig = new SysConfig();
        BeanUtils.copyProperties(sysConfigVO, sysConfig);
        return ResultUtils.success(sysConfigService.save(sysConfig));
    }
    @ApiOperation("删除")
    @PostMapping("delete/{id}")
    @ApiImplicitParam(name = "id", value = "id", dataType = "Long", paramType = "path", required = true)
    public Result delete(@PathVariable("id")Long id) {
        sysConfigService.deleteById(id);
        return ResultUtils.success();
    }
}
